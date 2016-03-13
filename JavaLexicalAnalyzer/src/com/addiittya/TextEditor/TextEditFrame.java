package com.addiittya.TextEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.addiittya.JavaLex.JavaLex;
import com.addiittya.JavaLex.Word;

public class TextEditFrame extends JFrame{
	
	private static final long serialVersionUID = 623L;
	private static SimpleAttributeSet defaultStyle;
	private static ArrayList<SimpleAttributeSet> styles;
	
	private JMenuItem saveitem;
	private InfoDialog dialog;
	private JTextPane textPane;
	private JPanel mainPane,countpanel;
	private JFileChooser chooser,savechooser;
	private File opened;
	private JPopupMenu popup;
	private JLabel count;
	private JButton bsave;
	private String charsetName = "UTF-8";
    private String newline = "\n";
    private HashMap<Object, Action> actions;
    private Updater updater = null;
    static private boolean updating = false;
	
    /**
     * Setup all styles
     */
	static {
		defaultStyle = new SimpleAttributeSet();
		StyleConstants.setForeground(defaultStyle, Color.BLACK);
		StyleConstants.setBold(defaultStyle, false);
		
		styles = new ArrayList<SimpleAttributeSet>(7);
		// default
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(0), Color.BLACK);
		StyleConstants.setBold(styles.get(0), false);
		// KEYWORD
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(1), new Color(132, 20, 92));		
		StyleConstants.setBold(styles.get(1), false);
		// IDENTIFIER
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(2), new Color(6, 24, 100));			
		StyleConstants.setBold(styles.get(2), false);
		// OPERATOR
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(3), new Color(197, 134, 58));				
		StyleConstants.setBold(styles.get(3), false);
		// CONSTANT
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(4), new Color(45, 49, 255));					
		StyleConstants.setBold(styles.get(3), false);
		// DELIMITER
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(5), Color.BLACK);					
		StyleConstants.setBold(styles.get(5), false);
		// COMMENT
		styles.add(new SimpleAttributeSet());
		StyleConstants.setForeground(styles.get(6), new Color(65, 126, 96));		
		StyleConstants.setBold(styles.get(6), false);
	}
	
	public TextEditFrame()
	{
		setTitle("Java Lexical Analyzer");
		setSize(800,600);
		centerFrame();
		
		mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		textPane = new JTextPane() {
		    /**
			 * Create a no wrap JTextPane
			 */
			private static final long serialVersionUID = 4654545721459475642L;

			public boolean getScrollableTracksViewportWidth() {
		        return getUI().getPreferredSize(this).width 
		            <= getParent().getSize().width;
		    }
		};
		
		/*
		 * Menu
		 */
		actions = createActionTable(textPane);
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);
		
		JMenuItem openitem = new JMenuItem("Open",new ImageIcon("img/open.png"));
		chooser = new JFileChooser();
		chooser.setFileFilter(new JavaSourceFilter());
		openitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		openitem.addActionListener( new FileOpenListener() );
		fileMenu.add(openitem);
		
		saveitem = new JMenuItem("Save",new ImageIcon("img/save.png"));
		saveitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveitem.addActionListener(new FileSaveListener());
		fileMenu.add(saveitem);
		saveitem.setEnabled(false);
		
		JMenuItem saveasitem = new JMenuItem("Save As",new ImageIcon("img/saveas.png"));
		savechooser = new JFileChooser();
		savechooser.setFileFilter(new JavaSourceFilter());
		saveasitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		saveasitem.addActionListener(new FileSaveAsListener());
		fileMenu.add(saveasitem);
		
		fileMenu.addSeparator();
		
		JMenuItem exititem = new JMenuItem("Exit",new ImageIcon("img/exit.png"));
		exititem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		exititem.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		} );
		fileMenu.add(exititem);
		
		JMenu editMenu = new JMenu("Edit");
		menu.add(editMenu);
		
		JMenuItem selectitem = new JMenuItem("Select All");
		selectitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		selectitem.addActionListener(getActionByName(DefaultEditorKit.selectAllAction));
		editMenu.add(selectitem);
		
		editMenu.addSeparator();
		
		JMenuItem cutitem = new JMenuItem("Cut",new ImageIcon("./img/cut.png"));
		cutitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		cutitem.addActionListener(getActionByName(DefaultEditorKit.cutAction));
		editMenu.add(cutitem);
		
		JMenuItem copyitem = new JMenuItem("Copy",new ImageIcon("./img/copy.png"));
		copyitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		copyitem.addActionListener(getActionByName(DefaultEditorKit.copyAction));
		editMenu.add(copyitem);
		
		JMenuItem pasteitem = new JMenuItem("Paste",new ImageIcon("./img/paste.png"));
		pasteitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		pasteitem.addActionListener(getActionByName(DefaultEditorKit.pasteAction));
		editMenu.add(pasteitem);
		
		editMenu.addSeparator();
		
		JMenuItem formatItem = new JMenuItem("Format", new ImageIcon("./img/format.png"));
		formatItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		formatItem.addActionListener(new FormatListener());
		editMenu.add(formatItem);
		
//		JMenu infomenu = new JMenu("?");
//		menu.add(infomenu);
		
//		JMenuItem infoitem = new JMenuItem("Info",new ImageIcon("./img/info.png"));
//		infoitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
//		infoitem.addActionListener(new InfoListener());
//		infomenu.add(infoitem);
		
//		POPUP MENU START
//		ADDING STANDARD ACTIONS
		popup = new JPopupMenu();
		
		JMenuItem cut = new JMenuItem("Cut",new ImageIcon("./img/cut.png"));
		cut.addActionListener(getActionByName(DefaultEditorKit.cutAction));
		popup.add(cut);
		
		JMenuItem copy = new JMenuItem("Copy",new ImageIcon("./img/copy.png"));
		copy.addActionListener(getActionByName(DefaultEditorKit.copyAction));
		popup.add(copy);
		
		JMenuItem paste = new JMenuItem("Paste",new ImageIcon("./img/paste.png"));
		paste.addActionListener(getActionByName(DefaultEditorKit.pasteAction));
		popup.add(paste);
		
		MouseListener popupListener = new PopupListener();
		
//		TOOLBAR START
		
		JToolBar tools = new JToolBar("Tools");
		
		JButton bopenf = new JButton(new ImageIcon("./img/open.png"));
		bopenf.addActionListener(new FileOpenListener());
		bopenf.setToolTipText("Open File");
		tools.add(bopenf);
		
		bsave = new JButton(new ImageIcon("./img/save.png"));
		bsave.addActionListener(new FileSaveListener());
		bsave.setToolTipText("Save");
		bsave.setEnabled(false);
		tools.add(bsave);
		
		JButton bsaveas = new JButton(new ImageIcon("./img/saveas.png"));
		bsaveas.addActionListener(new FileSaveAsListener());
		bsaveas.setToolTipText("Save As");
		tools.add(bsaveas);
		
		tools.addSeparator();
		
		JButton bcut = new JButton(new ImageIcon("./img/cut.png"));
		bcut.addActionListener(getActionByName(DefaultEditorKit.cutAction));
		bcut.setToolTipText("Cut");
		tools.add(bcut);
		
		JButton bcopy = new JButton(new ImageIcon("./img/copy.png"));
		bcopy.addActionListener(getActionByName(DefaultEditorKit.copyAction));
		bcopy.setToolTipText("Copy");
		tools.add(bcopy);
		
		JButton bpaste = new JButton(new ImageIcon("./img/paste.png"));
		bpaste.addActionListener(getActionByName(DefaultEditorKit.pasteAction));
		bpaste.setToolTipText("Paste");
		tools.add(bpaste);
		
		JButton bformat = new JButton(new ImageIcon("./img/format.png"));
		bformat.addActionListener(new FormatListener());
		bpaste.setToolTipText("Format");
		tools.add(bformat);
		
		tools.addSeparator();
		
		JButton binfo = new JButton(new ImageIcon("./img/info.png"));
		binfo.addActionListener( new InfoListener() );
		binfo.setToolTipText("Info");
		tools.add(binfo);
		
		JButton bexit = new JButton(new ImageIcon("./img/exit.png"));
		bexit.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}  );
		bexit.setToolTipText("Exit");
		tools.add(bexit);
		
		add(tools, BorderLayout.NORTH);
		
		
//		FORMATTING AREA
		
		Dimension fd = this.getSize();
		textPane.setBounds(new Rectangle(fd));
        textPane.setMargin(new Insets(5,5,5,5));
        textPane.setFont(new Font("Courier", Font.PLAIN, 14));
		JScrollPane scrolltxt = new JScrollPane(textPane);
		mainPane.add(scrolltxt,BorderLayout.CENTER);
		textPane.addMouseListener(popupListener);
		add(mainPane);
		
		countpanel = new JPanel();
		count = new JLabel();
		Count c  = new Count();
		Thread t = new Thread(c);
		t.start();
		countpanel.add(count);
		add(countpanel, BorderLayout.SOUTH);
		
		updater = new Updater();
		textPane.getDocument().addDocumentListener(updater);
		
	}
	
	
	class Updater implements DocumentListener {
		int count = 0;

		@Override
		public void changedUpdate(DocumentEvent e) {
			update(e.getDocument());
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			update(e.getDocument());
			
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
			update(e.getDocument());
			
		}
		
		private void update(Document document) {
//			System.err.println("updating: " + count + ", updatine: " + updating);
			if (!updating) {
				count++;
				if (count > 5) {
					try {
						Lex lex = new Lex();
						lex.setSource(document.getText(0, document.getLength()));
						Thread lexThread = new Thread(lex);
						lexThread.start();
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					count = 0;
				}
				
			}

		}
	}
	
	
	private class Lex implements Runnable {
		protected JavaLex analyzer;
		protected String source = "";
		
		public void run() {
			updateTextPane();
		}
		
		public void setSource(String source) {
			this.source = source;
		}
		
		private void updateTextPane() {
			if (updating) {
				return;
			}
			updating = true;
//			System.out.println("Start update");
			
			try {
				int caret = textPane.getCaretPosition();
				StyledDocument tempDoc = new DefaultStyledDocument();
				analyzer = new JavaLex(source);
				Word word = null;
				int index = 0;
				while (analyzer.hasNextWord()) {
//					System.out.println("0");
					word = analyzer.nextWord();
//					System.out.println("Word:" + word);
					if (word != null) {
//						System.out.println("A");
						tempDoc.insertString(tempDoc.getLength(), source.substring(index, word.getIndex()), styles.get(0));
//						System.out.println("B");
						tempDoc.insertString(tempDoc.getLength(), word.getValue(), styles.get(word.getType()));
//						System.out.println("C");
						index = word.getIndex() + word.getValue().length();
//						System.out.println("D");
					}
//					System.out.println("Has next:" + analyzer.hasNextWord());
				}
//				System.out.println("No more words");
				textPane.setDocument(tempDoc);
				caret = Math.min(tempDoc.getLength(),caret+1);
				textPane.setCaretPosition(caret);
				textPane.getDocument().addDocumentListener(updater);
//				System.err.println("Updated!");
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			
//			System.out.println("End update");
			updating = false;
		}
		
	}
	
	private class FormatLex implements Runnable {
		
		protected JavaLex analyzer;
		protected String source = "";
		private StyledDocument tempDoc;
		private Word word;
		private final String tab = "    ";
		private final String space = " ";
		
		private static final String NEWLINE_CHAR = "[{};]";
		private static final String FRONT_SPACE_CHAR = "[{]";
		private static final String BEHIND_SPACE_CHAR = "[},]";
		private static final String BOTH_SIDE_SPACE_CHAR = "[\\|&=+\\-/]|(==)|(!=)|(<=)|(>=)|(&&)|(\\|\\|)|(\\*)";
		
		public void run() {
			updateTextPane();
		}
		
		public void setSource(String source) {
			this.source = source;
		}
		
		private void updateTextPane() {
			if (updating) {
				return;
			}
			updating = true;
			
			int indent = 0;
			Word lastWord = null;
			try {
				System.out.println("Start update");

				tempDoc = new DefaultStyledDocument();
				analyzer = new JavaLex(source);
				word = null;
				while (analyzer.hasNextWord()) {
					word = analyzer.nextWord();
					if (word != null) {
						if (lastWord != null){
							if (lastWord.getType() == Word.COMMENT)
								insertNewline(indent);
							else if ((lastWord.getType() == Word.CONSTANT || lastWord.getType() == Word.IDENTIFIER || lastWord.getType() == Word.KEYWORD || lastWord.getType() == Word.UNDEFINED) && (word.getType() != Word.OPERATOR && word.getType() != Word.DELIMITER))
								tempDoc.insertString(tempDoc.getLength(), space, styles.get(0));
							else if (lastWord.getValue().matches(NEWLINE_CHAR) && ((word.getType() != Word.COMMENT) || (lastWord.getLine() < word.getLine()))) {
								insertNewline(indent);
							} else if (lastWord.getLine() < word.getLine())
								insertNewline(indent);
						}
						
						if (word.getValue().matches(FRONT_SPACE_CHAR) || word.getValue().matches(BOTH_SIDE_SPACE_CHAR))
							tempDoc.insertString(tempDoc.getLength(), space, styles.get(0));
						
						tempDoc.insertString(tempDoc.getLength(), word.getValue(), styles.get(word.getType()));
						
						if (word.getValue().matches(BEHIND_SPACE_CHAR) || word.getValue().matches(BOTH_SIDE_SPACE_CHAR))
							tempDoc.insertString(tempDoc.getLength(), space, styles.get(0));
						
						if (word.getValue().equals("{")) {
							indent++;
						} else if (word.getValue().equals("}")) {
							tempDoc.insertString(tempDoc.getLength(), newline, styles.get(0));
							indent--;
						}
						
						if (lastWord != null && lastWord.getValue().equals("@"))
							word = new Word(word.getLine(), lastWord.getRow(),lastWord.getIndex(), Word.COMMENT, lastWord.getValue() + word.getValue());
						lastWord = word;
					}
				}
				textPane.setDocument(tempDoc);
				textPane.getDocument().addDocumentListener(updater);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
			
			updating = false;
		}
		
		private void insertNewline(int indent) {
			try {
				tempDoc.insertString(tempDoc.getLength(), newline, styles.get(0));
				int ind = indent;
				if (word.getValue().equals("}"))
					ind--;
				
				for (int i=0; i<ind; ++i){
					tempDoc.insertString(tempDoc.getLength(), tab, styles.get(0));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private class Count implements Runnable
	{
		
		public void run()
		{
			while(true)
			{
				if(textPane.getText() == null) count.setText("0");
				String s = textPane.getText();
				StringTokenizer st = new StringTokenizer(s," "+newline);
				count.setText( "Words: " + st.countTokens() + " Characters: " + s.length() );
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			
		}
		
	}
	
	private class InfoDialog extends JDialog
	{
		private static final long serialVersionUID = 1L;
		
		public InfoDialog(JFrame fr)
		{
			super( fr, "Author", true );
			
			add(new JLabel("<html><h5>Developed by:</h5><br/><h1>addiittya</h1></html>"), BorderLayout.CENTER );
			JButton ok = new JButton("OK");
			ok.addActionListener( new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					setVisible(false);
				}
			});
			
			JPanel infop = new JPanel();
			infop.add(ok);
			add(infop,BorderLayout.SOUTH);
			setSize(200,100);
		}
		
		
	}
	
	public void centerFrame()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = getSize();
		setLocation ((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}
	
	
	private class PopupListener extends MouseAdapter 
	{
	    public void mousePressed(MouseEvent e)
	    {
	        maybeShowPopup(e);
	    }
	
	    public void mouseReleased(MouseEvent e)
	    {
	        maybeShowPopup(e);
	    }
	
	    private void maybeShowPopup(MouseEvent e)
	    {
	        if (e.isPopupTrigger())
	        {
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
	    }
	}

	private class InfoListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			if (dialog == null) dialog = new InfoDialog(TextEditFrame.this);
			dialog.setVisible(true);
						
		}
		
	}

	private class FileSaveAsListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			
			int ris = savechooser.showSaveDialog(TextEditFrame.this);
			if(ris == JFileChooser.APPROVE_OPTION)
			{
				saveitem.setEnabled(true);
				bsave.setEnabled(true);
				setTitle("TextEdit - " + savechooser.getSelectedFile());
				FileWriter filew = null;
				try {
					filew = new FileWriter(savechooser.getSelectedFile());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				PrintWriter scrivi = new PrintWriter(filew);
				scrivi.print(textPane.getText());
				scrivi.close();
				
			}
			
		}
		
		
		
	}
	
	private class FileSaveListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e) 
		{
			
			FileWriter filew = null;
			try {
				filew = new FileWriter(opened);
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			PrintWriter scrivi = new PrintWriter(filew);
			
			String ris = textPane.getText();
			
			scrivi.print(ris);
			
			scrivi.close();
			
		}
		
	}
	
	private class FileOpenListener implements ActionListener
	{
		InputStreamReader in;
		public void actionPerformed(ActionEvent e)
		{	
			int ris = chooser.showOpenDialog(TextEditFrame.this);
			
			if(ris == JFileChooser.APPROVE_OPTION)
			{
				saveitem.setEnabled(true);
				bsave.setEnabled(true);
				opened = chooser.getSelectedFile();
				setTitle("Java Lex - " + opened);
				String source = "";
				
				try {
					in = new InputStreamReader(new FileInputStream(chooser.getSelectedFile()),charsetName);
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				BufferedReader br = new BufferedReader(in);
				try {
					String s = br.readLine();
					while( s != null )
					{
						source = source.concat(s + '\n');
						s = br.readLine();
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				while (updating) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Lex lex = new Lex();
				lex.setSource(source);
				Thread lexThread = new Thread(lex);
				lexThread.start();
//				textPane.setCaretPosition(0);	// jump to the begin of the new file				
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		
	}
	
	private class FormatListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Formater");
			String source = "";
			try {
				source = textPane.getDocument().getText(0, textPane.getDocument().getLength());
				FormatLex lex = new FormatLex();
				lex.setSource(source);
				Thread lexThread = new Thread(lex);
				lexThread.start();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	private class JavaSourceFilter extends FileFilter
	{
		
		public boolean accept(File f) 
		{
			return f.getName().toLowerCase().endsWith(".java") || f.isDirectory();
		}

		public String getDescription()
		{
			return "Java Source File";
		}
			
	}
	
	public static int countWord(String in)
	{
		StringTokenizer st = new StringTokenizer(in," ");
		
		return st.countTokens();
		
	}
	
    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
        HashMap<Object, Action> actions = new HashMap<Object, Action>();
        Action[] actionsArray = textComponent.getActions();
        for (int i = 0; i < actionsArray.length; i++) {
            Action a = actionsArray[i];
            actions.put(a.getValue(Action.NAME), a);
        }
	return actions;
    }

    private Action getActionByName(String name) {
        return actions.get(name);
    }
	
}
