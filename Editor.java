import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Editor implements ActionListener{
JLabel outputl;String fname;File file;
JTextArea editor,output1;
JMenuBar menuBar;JLabel label;
JFrame frame;JPanel panel;
JMenu menu1,menu2,menu3,menu4,menu5;
JMenuItem mitem1,mitem2,mitem3,mitem4,mitem5,mitem6,mitem7,mitem8,mitem9,mitem10,mitem11,mitem12,mitem13,mitem14,mitem15;
JTabbedPane tab;
KeyStroke  i;

Editor(){
frame=new JFrame("Mohit's Editor");
frame.setLayout(null);
frame.setSize(1200,600);
frame.setResizable(false);
frame.setBackground(Color.WHITE);	
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	initComponents();
}

private void initComponents(){
panel=new JPanel();
menuBar=new JMenuBar();
						//file

menu1=new JMenu("File");
										//NEW
mitem1=new JMenuItem("New",KeyEvent.VK_N);
mitem2=new JMenuItem("Open",KeyEvent.VK_O);
mitem3=new JMenuItem("Save",KeyEvent.VK_S);
mitem4=new JMenuItem("Save as");
mitem5=new JMenuItem("Exit",KeyEvent.VK_E);
menu1.add(mitem1);mitem1.addActionListener(this);
menu1.add(mitem2);mitem2.addActionListener(this);
menu1.add(mitem3);mitem3.addActionListener(this);
menu1.add(mitem4);mitem4.addActionListener(this);
menu1.add(mitem5);mitem5.addActionListener(this);
menuBar.add(menu1);
										//open
menu2=new JMenu("Edit");
mitem6=new JMenuItem("cut");
mitem7=new JMenuItem("copy");
mitem8=new JMenuItem("paste");
mitem9=new JMenuItem("Find");
menu2.add(mitem6);mitem6.addActionListener(this);
menu2.add(mitem7);mitem7.addActionListener(this);
menu2.add(mitem8);mitem8.addActionListener(this);
menu2.add(mitem9);mitem9.addActionListener(this);
menuBar.add(menu2);

menu3=new JMenu("Format");

mitem10=new JMenuItem("FontSize");
mitem11=new JMenuItem("FontColor");
mitem12=new JMenuItem("Background");

menu3.add(mitem10);mitem10.addActionListener(this);
menu3.add(mitem11);mitem11.addActionListener(this);
menu3.add(mitem12);mitem12.addActionListener(this);
menuBar.add(menu3);


menu4=new JMenu("Compile/Run");

mitem13=new JMenuItem("Compile");
mitem14=new JMenuItem("Run");


menu4.add(mitem13);mitem13.addActionListener(this);
menu4.add(mitem14);mitem14.addActionListener(this);
menuBar.add(menu4);

file=new File(System.getProperty("user.dir"));
tab=new JTabbedPane();
			frame.add(tab);
tab.setSelectedIndex(tab.getTabCount()-1);
frame.add(tab);
panel.add(tab);
frame.add(panel);
editor=new JTextArea();
output1=new JTextArea();
outputl=new JLabel("Output");
outputl.setBackground(Color.BLACK);
outputl.setBounds(800,0,400,50);
outputl.setForeground(Color.WHITE);
frame.add(outputl);
outputl.setHorizontalAlignment(JLabel.CENTER);
editor.setBounds(0,0,800,600);
output1.setBounds(800,30,400,500);
frame.add(output1);output1.setEditable(false);
output1.setBackground(Color.BLACK);output1.setForeground(Color.WHITE);
JScrollPane scroll=new JScrollPane(editor);
frame.add(scroll);
 			editor.setLineWrap(true);
			editor.setWrapStyleWord(true);
scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
frame.add(editor);
frame.setJMenuBar(menuBar);
frame.setVisible(true);
}

public void actionPerformed(ActionEvent ae){
if(ae.getActionCommand().equals("New")){
	editor.setText("");
}
else if(ae.getActionCommand().equals("Open")){
	JFileChooser jfc = new JFileChooser();

		int returnValue = jfc.showOpenDialog(frame);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			

              int i;
              String code="";
              try{


              	File selectedFile = jfc.getSelectedFile();
              frame.setTitle(selectedFile.getName());
              BufferedReader buffer=new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));
file=selectedFile;
              while(((i=buffer.read()))!=-1){
              	code+=(char)i;
              }
              editor.setText(code);
			System.out.println(selectedFile.getName());
		}
		catch(FileNotFoundException fen){
			System.out.println(fen);
		}
catch(Exception e){System.out.println("exception occured"+e);}

	}}
else if(ae.getActionCommand().equals("Save")){
	JFileChooser jfc = new JFileChooser();
	try{
jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));

		int returnValue = jfc.showSaveDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			frame.setTitle(selectedFile.getName());
file=selectedFile;
			FileWriter fileWriter = new FileWriter(selectedFile.getName());
				fileWriter.write(editor.getText());
				fileWriter.close();



			System.out.println(selectedFile.getAbsolutePath());
		}}catch(Exception e){

		}

	
}
else if(ae.getActionCommand().equals("Save as")){
	if(frame.getTitle().equals("")){
JFileChooser jfc = new JFileChooser();
	try{
jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));

		int returnValue = jfc.showSaveDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			frame.setTitle(selectedFile.getName());

			FileWriter fileWriter = new FileWriter(selectedFile.getName());
				fileWriter.write(editor.getText());
				fileWriter.close();



			System.out.println(selectedFile.getAbsolutePath());
		}}catch(Exception e){

		}

	}
	
}
else if(ae.getActionCommand().equals("Exit")){
System.exit(0);}

else if(ae.getActionCommand().equals("cut")){
editor.cut();}

else if(ae.getActionCommand().equals("copy")){
editor.copy();}

else if(ae.getActionCommand().equals("paste")){
editor.paste();}

else if(ae.getActionCommand().equals("About Developer")){
//editor.undo();
	


}

else if(ae.getActionCommand().equals("Compile")){
try{
fname=file.getName();}
		catch(Exception e){
if(fname==null){
	fname=frame.getTitle();
}}
String resultErr="";
try{
	System.out.println(fname);
	String[] command = {"C:\\Program Files\\Java\\jdk1.7.0_71\\bin\\javac.exe",fname};
	ProcessBuilder pb=new ProcessBuilder(command);
	
			Process p=pb.start();
						BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
						while(true)
						{
							String temp = err.readLine();
							if(temp!=null)
							{
								resultErr += temp;
								resultErr +="\n";
							}
							else
								break;
						}
						if(resultErr.equals("") || resultErr.trim().equals("Note: "+fname+" uses unchecked or unsafe operations.\nNote: Recompile with -Xlint:unchecked for details."))
						{
							if(!resultErr.trim().equals("Note: "+fname+" uses unchecked or unsafe operations.\nNote: Recompile with -Xlint:unchecked for details."))
							{
								output1.setText("COMPILATION SUCCESSFUL  : "+ fname);
								System.out.println("COMPILATION SUCCESSFUL  : "+ fname);
								err.close();}}
}catch(Exception e){
System.out.println(e);

}






}

else if(ae.getActionCommand().equals("Run")){
String resultErr="";
		try{
			fname=file.getName();}
					catch(Exception e){
			if(fname==null){
				fname=frame.getTitle();
			}}
		try{
			String[] command = {"C:\\Program Files\\Java\\jdk1.7.0_71\\bin\\java.exe ",fname.replaceAll(".java","")};
			
			
								 resultErr="";String resultOutput="";
								ProcessBuilder pb=new ProcessBuilder(command);
						//	 Process process =  runtime.exec("C:\\Program Files\\Java\\jdk1.7.0_71\\bin\\java.exe "+fname.replace(".java", ".class"));
								pb.directory(new File("C:\\Users\\lenovo\\Desktop\\college\\Editor"));
								Process process=pb.start();
								BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
								BufferedReader error1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
								while(true)
								{
									String temp = output.readLine();
									if(temp!=null)
									{	resultOutput += temp;
										resultOutput += "\n";
									}
									else{
										break;
									}
								}
								while(true)
								{
									String temp = error1.readLine();
									if(temp!=null)
									{System.out.println(temp+" hi");
									output1.setText(temp);
										resultErr += temp;										resultErr += "\n";
									}
									else
										break;
								}
								output.close();
								error1.close();
								if(!resultOutput.equals("") && resultErr.equals("")){
									System.out.println(resultOutput);
									output1.setText(resultOutput);}
								if(resultOutput.equals("")){
									System.out.println(resultErr);
									output1.setText(resultErr);}
//								if(!resultOutput.equals("") && !resultErr.equals(""))
//									System.out.println(resultOutput + "\n" + resultErr);
//							
										else
										{
											System.out.println("Note: "+fname+" uses unchecked or unsafe operations.\nNote: Recompile with -Xlint:unchecked for details.\nCOMPILATION SUCCESSFUL  : "+ fname);
									
										}
									
			}catch(Exception e){System.out.println(e);
	}

}




}


public static void main(String [] args)
{
	new Editor();
}
}