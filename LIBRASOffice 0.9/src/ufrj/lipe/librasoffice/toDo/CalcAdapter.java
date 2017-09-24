package ufrj.lipe.librasoffice.toDo;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XDispatchHelper;
import com.sun.star.frame.XDispatchProvider;
import com.sun.star.frame.XFrame;
import com.sun.star.uno.UnoRuntime;

import ufrj.lipe.librasoffice.LASOFront;

/**
 * Class CalcAdapter
 */
public class CalcAdapter {

  //
  // Fields
  //
  static private FormulaHelperGUI fgui = null;
  
  
  //
  // Constructors
  //
  public CalcAdapter () {
	  System.out.println(LASOFront.LODesktop);
	  soma("A1","A10","A11");
	  fgui = new FormulaHelperGUI();
	  fgui.getFrame().setVisible(false);
  };
  
  //
  // Methods
  //
 static void soma(String Inicial , String Final, String Resul){
         PropertyValue[] args1 = new PropertyValue[1];
         args1[0] = new PropertyValue();
         args1[0].Name = "ToPoint";
         args1[0].Value = Resul;
         
         XDesktop xDesk = (XDesktop)UnoRuntime.queryInterface(XDesktop.class, LASOFront.LODesktop);
         System.err.println(xDesk.toString());
         XFrame dFrame = xDesk.getCurrentFrame();
         while (dFrame == null) dFrame = xDesk.getCurrentFrame();
         System.err.println(dFrame.toString());
         XDispatchProvider xDProver = UnoRuntime.queryInterface(XDispatchProvider.class, dFrame);
         System.err.println(xDProver.toString());

         
         LASOFront.xDHelper.executeDispatch(xDProver, ".uno:GoToCell", "", 0, args1);
         PropertyValue[] args2 = new PropertyValue[1];
         args2[0] = new PropertyValue();
         args2[0].Name = "StringName";
         args2[0].Value = "=SOMA("+Inicial+":"+Final+")";
         /*if ("SUB".equals(currOpt)) args2[0].Value = "="+Inicial+"-"+Final;
         if ("DIV".equals(currOpt)) args2[0].Value = "="+Inicial+"/"+Final;
         if ("MULT".equals(currOpt)) args2[0].Value = "=MULT("+Inicial+":"+Final+")";*/
         LASOFront.xDHelper.executeDispatch(xDProver, ".uno:EnterString", "", 0, args2);      
}

  //
  // Accessor methods
  //
static public FormulaHelperGUI getGUI (){
	return fgui;
}
  //
  // Other methods
  //

}
