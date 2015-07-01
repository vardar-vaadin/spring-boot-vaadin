package tr.biz.vardar.view;

import tr.biz.vardar.event.UIEvent.CloseOpenWindowsEvent;
import tr.biz.vardar.event.UIEventBus;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public abstract class CustomView extends Panel implements View {

	public static final String EDIT_ID = "dashboard-edit";
	public static final String TITLE_ID = "dashboard-title";

	private VerticalLayout dashboardPanels;
	private final VerticalLayout root;
	protected HorizontalLayout header;

	public CustomView() {
		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setSizeFull();
		UIEventBus.register(this);

		root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(false);
		root.addStyleName("dashboard-view");
		setContent(root);
		Responsive.makeResponsive(root);

		root.addComponent(buildHeader());
		Component component = buildContent();
		root.addComponent(component);
		root.setExpandRatio(component, 1);
		root.addLayoutClickListener(new LayoutClickListener() {
			@Override
			public void layoutClick(final LayoutClickEvent event) {
				UIEventBus.post(new CloseOpenWindowsEvent());
			}
		});

		setHeaderCrumb();
	}

	private void setHeaderCrumb(){
		Label headerCrumbLayout = addHeaderCrumb();
		headerCrumbLayout.setWidth("100%");
		header.addComponent(headerCrumbLayout);
		//		header.setComponentAlignment(headerCrumbLayout, Alignment.MIDDLE_LEFT);
	}


	private Component buildHeader() {
		header = new HorizontalLayout();
		header.setWidth("100%");
		header.addStyleName("viewheader");
		header.setSpacing(true);
		header.removeAllComponents();
		header.setSpacing(false);
		header.setMargin(false);

		return header;
	}

	public abstract Label addHeaderCrumb();


	private Component buildContent() {
		dashboardPanels = new VerticalLayout();
		dashboardPanels.addStyleName("dashboard-panels");
		Component c = createComponent();
		if(c != null) {
			dashboardPanels.addComponent(createContentWrapper(c));
		}
		//		Responsive.makeResponsloive(dashboardPanels);
		return dashboardPanels;
	}

	protected void replaceComponent(Component c) {
		dashboardPanels.removeAllComponents();
		dashboardPanels.addComponent(createContentWrapper(c));
	}

	public abstract Component createComponent();

	private Component createContentWrapper(final Component content) {
		final CssLayout slot = new CssLayout();
		slot.setWidth("100%");
		slot.addStyleName("dashboard-panel-slot");
		slot.addStyleName(ValoTheme.LAYOUT_CARD);
		slot.setHeight("100%");
		content.setHeight("100%");
		slot.addComponent(content);
		return slot;
	}

}
