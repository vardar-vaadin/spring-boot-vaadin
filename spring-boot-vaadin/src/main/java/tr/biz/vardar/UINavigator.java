package tr.biz.vardar;


import tr.biz.vardar.event.UIEventBus;
import tr.biz.vardar.event.UIEvent.BrowserResizeEvent;
import tr.biz.vardar.event.UIEvent.CloseOpenWindowsEvent;
import tr.biz.vardar.event.UIEvent.PostViewChangeEvent;
import tr.biz.vardar.view.UIViewType;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class UINavigator extends Navigator {

    // Provide a Google Analytics tracker id here

    private static final UIViewType ERROR_VIEW = UIViewType.DEMO;
    private ViewProvider errorViewProvider;

    public UINavigator(final ComponentContainer container) {
        super(UI.getCurrent(), container);

        initViewChangeListener();
        initViewProviders();

    }


    private void initViewChangeListener() {
        addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(final ViewChangeEvent event) {
                // Since there's no conditions in switching between the views
                // we can always return true.
                return true;
            }

            @Override
            public void afterViewChange(final ViewChangeEvent event) {
                UIViewType view = UIViewType.getByViewName(event
                        .getViewName());
                // Appropriate events get fired after the view is changed.
                UIEventBus.post(new PostViewChangeEvent(view));
                UIEventBus.post(new BrowserResizeEvent());
                UIEventBus.post(new CloseOpenWindowsEvent());

            }
        });
    }

    private void initViewProviders() {
        // A dedicated view provider is added for each separate view type
        for (final UIViewType viewType : UIViewType.values()) {
            ViewProvider viewProvider = new ClassBasedViewProvider(
                    viewType.getViewName(), viewType.getViewClass()) {

                // This field caches an already initialized view instance if the
                // view should be cached (stateful views).
                private View cachedInstance;

                @Override
                public View getView(final String viewName) {
                    View result = null;
                    if (viewType.getViewName().equals(viewName)) {
                        if (viewType.isStateful()) {
                            // Stateful views get lazily instantiated
                            if (cachedInstance == null) {
                                cachedInstance = super.getView(viewType
                                        .getViewName());
                            }
                            result = cachedInstance;
                        } else {
                            // Non-stateful views get instantiated every time
                            // they're navigated to
                            result = super.getView(viewType.getViewName());
                        }
                    }
                    return result;
                }
            };

            if (viewType == ERROR_VIEW) {
                errorViewProvider = viewProvider;
            }

            addProvider(viewProvider);
        }

        setErrorProvider(new ViewProvider() {
            @Override
            public String getViewName(final String viewAndParameters) {
                return ERROR_VIEW.getViewName();
            }

            @Override
            public View getView(final String viewName) {
                return errorViewProvider.getView(ERROR_VIEW.getViewName());
            }
        });
    }
}
