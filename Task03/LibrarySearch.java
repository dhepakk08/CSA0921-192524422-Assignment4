import java.awt.*; import java.awt.event.*;
public class LibrarySearch extends Frame {
 public LibrarySearch(){super("Campus Library Search");setLayout(new BorderLayout(8,8)); Label title=new Label("Campus Library",Label.CENTER);add(title,BorderLayout.NORTH);
 Panel search=new Panel(new FlowLayout(FlowLayout.LEFT)); search.add(new Label("Search")); search.add(new TextField(24)); Choice category=new Choice(); for(String c:new String[]{"All","Books","Journals","Theses"})category.add(c);search.add(category);search.add(new Button("Search"));
 Panel result=new Panel(new BorderLayout()); result.add(new TextArea("Search results appear here",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY),BorderLayout.CENTER);
 Panel controls=new Panel(new FlowLayout());controls.add(new Button("Prev"));controls.add(new Button("Next"));controls.add(new Button("Reserve")); Panel south=new Panel(new BorderLayout());south.add(controls,BorderLayout.CENTER);south.add(new Label("Ready"),BorderLayout.SOUTH);
 Panel center=new Panel(new BorderLayout(6,6));center.add(search,BorderLayout.NORTH);center.add(result,BorderLayout.CENTER);center.add(south,BorderLayout.SOUTH);add(center,BorderLayout.CENTER);addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){dispose();}});setSize(720,480);setLocationRelativeTo(null); }
 public static void main(String[]a){EventQueue.invokeLater(()->new LibrarySearch().setVisible(true));}
}
