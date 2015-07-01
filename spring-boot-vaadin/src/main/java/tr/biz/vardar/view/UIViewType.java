package tr.biz.vardar.view;

import tr.biz.vardar.view.demo.DemoView;
import tr.biz.vardar.view.demo.SecondDemoView;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum UIViewType {
    DEMO("demo", DemoView.class, FontAwesome.HOME, true),
    SECOND_DEMO("sdemo", SecondDemoView.class, FontAwesome.HOME, true);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private UIViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static UIViewType getByViewName(final String viewName) {
        UIViewType result = null;
        for (UIViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
