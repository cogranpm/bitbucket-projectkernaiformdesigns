package com.parinherm.form;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class NavigationShell extends ApplicationWindow {
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object inputElement) {
			return ArrayContentProvider.getInstance().getElements(inputElement);
		}
		public Object[] getChildren(Object parentElement) {
			return new Object[] { "item_0", "item_1", "item_2" };
		}
		public Object getParent(Object element) {
			return null;
		}
		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return super.getText(element);
		}
	}
	
	private final String[] navigationData = new String[] { "Models", "Scripts", "Templates", "Environments", "Builds" };
	
	private final String[] getNavigationData()
	{
		return navigationData;
	}

	/**
	 * Create the application window.
	 */
	public NavigationShell() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite shellParent = new Composite(container, SWT.NONE);
		shellParent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shellParent, SWT.NONE);
		
		Composite shellNavTree = new Composite(sashForm, SWT.NONE);
		shellNavTree.setLayout(new GridLayout(1, true));
		
		Composite shellNavTreeTitle = new Composite(shellNavTree, SWT.NONE);
		shellNavTreeTitle.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(shellNavTreeTitle, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 58, 17);
		lblNewLabel.setText("New Label");
		
		Composite shellNavTreeBody = new Composite(shellNavTree, SWT.NONE);
		shellNavTreeBody.setLayout(new FillLayout(SWT.VERTICAL));
		GridData gd_shellNavTreeBody = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_shellNavTreeBody.heightHint = 209;
		gd_shellNavTreeBody.widthHint = 152;
		shellNavTreeBody.setLayoutData(gd_shellNavTreeBody);
		
		TreeViewer treeViewer = new TreeViewer(shellNavTreeBody, SWT.BORDER);
		treeViewer.setColumnProperties(new String[] {"Name"});
		Tree tree = treeViewer.getTree();
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new ViewerLabelProvider());
		
		Composite shellNavMain = new Composite(sashForm, SWT.NONE);
		shellNavMain.setLayout(new FillLayout(SWT.HORIZONTAL));
		sashForm.setWeights(new int[] {1, 4});

		treeViewer.setInput(getNavigationData());
		
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			NavigationShell window = new NavigationShell();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(951, 542);
	}
}
