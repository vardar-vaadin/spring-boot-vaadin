package tr.biz.vardar;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tr.biz.vardar.domain.User;
import tr.biz.vardar.event.UIEvent.BrowserResizeEvent;
import tr.biz.vardar.event.UIEvent.CloseOpenWindowsEvent;
import tr.biz.vardar.event.UIEvent.UserLoggedOutEvent;
import tr.biz.vardar.event.UIEvent.UserLoginRequestedEvent;
import tr.biz.vardar.event.UIEventBus;
import tr.biz.vardar.service.DBService;
import tr.biz.vardar.view.LoginView;
import tr.biz.vardar.view.MainView;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@Theme("dashboard")
@Widgetset("tr.biz.vardar.DashboardWidgetSet")
@Title("Vardar Template")
@Component(value="ui")
@Scope("prototype")
@PreserveOnRefresh
@SuppressWarnings("serial")
@SpringUI
public final class MyUI extends UI {

	private final UIEventBus uiEventbus = new UIEventBus();

	@Autowired
	private DBService dbService;

	@Override
	protected void init(final VaadinRequest request) {
		setLocale(Locale.US);

		UIEventBus.register(this);
		Responsive.makeResponsive(this);
		addStyleName(ValoTheme.UI_WITH_MENU);

		updateContent();

		// Some views need to be aware of browser resize events so a
		// BrowserResizeEvent gets fired to the event bus on every occasion.
		Page.getCurrent().addBrowserWindowResizeListener(
				new BrowserWindowResizeListener() {
					@Override
					public void browserWindowResized(
							final BrowserWindowResizeEvent event) {
						UIEventBus.post(new BrowserResizeEvent());
					}
				});
	}

	/**
	 * Updates the correct content for this UI based on the current user status.
	 * If the user is logged in with appropriate privileges, main view is shown.
	 * Otherwise login view is shown.
	 */
	private void updateContent() {
		User user = (User) VaadinSession.getCurrent().getAttribute(
				User.class.getName());
		if (user != null) {
			// Authenticated user
			setContent(new MainView());
			removeStyleName("loginview");
			getNavigator().navigateTo(getNavigator().getState());
		} else {
			setContent(new LoginView());
			addStyleName("loginview");
		}
	}

	@Subscribe
	public void userLoginRequested(final UserLoginRequestedEvent event) {
		System.out.println("user login requested works");
		if(event.getUserName().length()>0 && event.getPassword().length()>0){
			User user = new User(event.getUserName(),event.getPassword());
			dbService.createUser(user);
			VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
			updateContent();
		}
		else{
			Notification.show("Write any username and password");
		}
	}

	@Subscribe
	public void userLoggedOut(final UserLoggedOutEvent event) {
		// When the user logs out, current VaadinSession gets closed and the
		// page gets reloaded on the login screen. Do notice the this doesn't
		// invalidate the current HttpSession.
		VaadinSession.getCurrent().close();
		Page.getCurrent().reload();
	}

	@Subscribe
	public void closeOpenWindows(final CloseOpenWindowsEvent event) {
		for (Window window : getWindows()) {
			window.close();
		}
	}

	public static UIEventBus getUIEventbus() {
		return ((MyUI) getCurrent()).uiEventbus;
	}
}
