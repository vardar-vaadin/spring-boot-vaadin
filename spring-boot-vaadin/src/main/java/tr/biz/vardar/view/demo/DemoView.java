package tr.biz.vardar.view.demo;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import tr.biz.vardar.view.CustomView;

@SuppressWarnings("serial")
public class DemoView extends CustomView{

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

	@Override
	public Label addHeaderCrumb() {
		return new Label("Demo");
	}

	@Override
	public Component createComponent() {
		Label label = new Label("Demo works");
		label.setStyleName(ValoTheme.LABEL_H1);
		return label;
	}
}
