package gui;

import java.util.EventListener;

public interface FormPanelListener extends EventListener {
	public void addRecetteEventOccured(FormEvent evt);
	public void findRecetteEventOccured(FormEvent evt);
}
