package tr.biz.vardar;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@Theme("valo")
@Title("Vaadin")
@SpringUI(path="vaadinui")
public class VaadinUI extends UI{

	@Override
	protected void init(VaadinRequest request) {
		Label label = new Label("Application works");
		setContent(label);
	}
	

}
