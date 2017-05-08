package ufrj.lipe.librasoffice;

import java.awt.EventQueue;

import com.artofsolving.jodconverter.openoffice.connection.AbstractOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XDispatchHelper;
import com.sun.star.frame.XDispatchProvider;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XModel;
import com.sun.star.lang.XComponent;
//import com.artofsolving.jodconverter.openoffice.connection.PipeOpenOfficeConnection;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.table.XCell;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import ufrj.lipe.librasoffice.librastooltip.AnimatedTooltip;

// Make sure LibreOffice is started with the proper arguments to have it listen before
// running this client application. Use one of the following commands to start it:
//  * For socket connection: soffice --accept="socket,host=localhost,port=8100,tcpNoDelay=1;urp;"
//  * For pipe connection:   soffice --accept="pipe,name=yourpipename;urp;" 
public class SetEnv {
    static AnimatedTooltip LIBRASHelp = null;
    static LoggedController LC = null;
    static Object LODesktop = null;
    static XDispatchHelper xDHelper = null;
	
    public static void main(String[] args) {
        AbstractOpenOfficeConnection cnx = null;
        
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {*/
					LIBRASHelp = new AnimatedTooltip();
					LIBRASHelp.getFrame().setVisible(true);
			        LC = new LoggedController(); //implementar esquema notify command
			        Thread threadLog = new Thread(LC);
			        threadLog.start();
		            while (!(LC.getOpen()) && LC.getFound()) {System.err.println("LASO.log ainda não encontrado e/ou aberto.");}
			        if (!(LC.getFound())) System.exit(1);
			        System.err.println("LASO.log foi encontrado e aberto!");/*
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});       */
		
        try {
            cnx = new SocketOpenOfficeConnection( "localhost", 8100 );
            System.out.println("Connecting to OOo...");
            cnx.connect();
            
            System.out.println( "Trying to get the component context" );
            if ( cnx.getComponentContext() != null ) {
                XComponentContext xCtx = cnx.getComponentContext();
                
                XMultiComponentFactory xCCSM = xCtx.getServiceManager();
                // get Desktop instance
                LODesktop = xCCSM.createInstanceWithContext ("com.sun.star.frame.Desktop", xCtx);

                Object disper = xCCSM.createInstanceWithContext("com.sun.star.frame.DispatchHelper", xCtx);
                xDHelper = UnoRuntime.queryInterface(XDispatchHelper.class, disper);
                
                CalcAdapter calcad = new CalcAdapter();
                
                //XDesktop xDesk = (XDesktop)UnoRuntime.queryInterface(XDesktop.class, desktop);
                

                /* REF https://wiki.openoffice.org/wiki/Documentation/DevGuide/OfficeDev/Dispatch_Results */
                
                /*
                XFrame dFrame = xDesk.getCurrentFrame();
                while (dFrame == null) dFrame = xDesk.getCurrentFrame();
                XDispatchProvider xDProver = UnoRuntime.queryInterface(XDispatchProvider.class, dFrame);
                
                System.out.println(xDesk.getCurrentComponent().toString());
                XComponent xC = xDesk.getCurrentComponent();
               
                XModel xM = UnoRuntime.queryInterface(XModel.class,xC);
                
                System.out.println(xM.getURL());            
                
                XSpreadsheetDocument xSpreadsheetDocument = UnoRuntime.queryInterface(XSpreadsheetDocument.class,xC);

XSpreadsheets xSpreadsheets = xSpreadsheetDocument.getSheets();
xSpreadsheets.insertNewByName("MySheet", (short)0);
com.sun.star.uno.Type elemType = xSpreadsheets.getElementType();

System.out.println(elemType.getTypeName());
Object sheet = xSpreadsheets.getByName("MySheet");
XSpreadsheet xSpreadsheet = UnoRuntime.queryInterface(
  XSpreadsheet.class, sheet);

XCell xCell = xSpreadsheet.getCellByPosition(0, 0);
xCell.setValue(21);
xCell = xSpreadsheet.getCellByPosition(0, 1);
xCell.setValue(21);
xCell = xSpreadsheet.getCellByPosition(0, 2);
xCell.setFormula("=sum(A1:A2)");
               */
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*if ( cnx != null ) {
                System.out.println( "Disconnecting from OOo..." );
                cnx.disconnect();
            }*/
        }
    }
}