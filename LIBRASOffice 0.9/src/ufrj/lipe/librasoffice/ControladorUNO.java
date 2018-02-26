package ufrj.lipe.librasoffice;

import com.artofsolving.jodconverter.openoffice.connection.AbstractOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.sun.star.frame.XController;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XDispatchHelper;
import com.sun.star.frame.XModel;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.XComponent;
//import com.artofsolving.jodconverter.openoffice.connection.PipeOpenOfficeConnection;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetView;
import com.sun.star.table.XCell;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

// Make sure LibreOffice is started with the proper arguments to have it listen before
// running this client application. Use one of the following commands to start it:
//  * For socket connection: soffice --accept="socket,host=localhost,port=8100,tcpNoDelay=1;urp;"
//  * For pipe connection:   soffice --accept="pipe,name=yourpipename;urp;"

public class ControladorUNO {
	private Object LODesktop;
	private XDispatchHelper xDHelper;
	private XDesktop xDesk;

	public ControladorUNO(){
		AbstractOpenOfficeConnection cnx = null;

		try {
			cnx = new SocketOpenOfficeConnection("localhost", 8100);
			System.out.println("Connecting to OOo...");
			cnx.connect();

			System.out.println("Trying to get the component context");
			if (cnx.getComponentContext() != null) {
				XComponentContext xCtx = cnx.getComponentContext();

				XMultiComponentFactory xCCSM = xCtx.getServiceManager();
				LODesktop = xCCSM.createInstanceWithContext("com.sun.star.frame.Desktop", xCtx);

				Object disper = xCCSM.createInstanceWithContext("com.sun.star.frame.DispatchHelper", xCtx);
				xDHelper = UnoRuntime.queryInterface(XDispatchHelper.class, disper);

				xDesk = (XDesktop) UnoRuntime.queryInterface(XDesktop.class, LODesktop);
			}
		}
		catch (Exception e) { e.printStackTrace(); }
		/*finally {
			if (cnx != null) {
				System.out.println("Disconnecting from OOo...");
				cnx.disconnect();
			}
		}*/
	}
	
	public void testarRotina() throws IndexOutOfBoundsException {
				XComponent xC = xDesk.getCurrentComponent();

				XModel xM = UnoRuntime.queryInterface(XModel.class,xC);

				XController xSpreadsheetController = xM.getCurrentController();
				
				XSpreadsheetView xSpreadsheetView = (XSpreadsheetView) UnoRuntime.queryInterface(XSpreadsheetView.class, xSpreadsheetController);

				XSpreadsheet xSpreadsheet = xSpreadsheetView.getActiveSheet();

				XCell xCell = xSpreadsheet.getCellRangeByName("A1").getCellByPosition(0, 0);
				//XCell xCell = xSpreadsheet.getCellByPosition(0, 0);
				xCell.setValue(485);
				xSpreadsheet.getCellRangeByName("A10").getCellByPosition(0, 0).setValue(1);
				xSpreadsheet.getCellRangeByName("B5").getCellByPosition(0, 0).setValue(45.3);

				xCell = xSpreadsheet.getCellRangeByName("C1").getCellByPosition(0, 0);
				xCell.setFormula("=sum(A1:B10)");
				xCell = xSpreadsheet.getCellRangeByName("C2").getCellByPosition(0, 0);
				xCell.setFormula("=sum(A1;A10)");
				xCell = xSpreadsheet.getCellRangeByName("d1").getCellByPosition(0, 0);
				xCell.setFormula("=max(A1:B5)"); 
	}
	
	public void insereFormula(String iC, String fC, String rC, String formula, boolean isRange) throws IndexOutOfBoundsException {
		System.err.println(iC+"-"+fC+"-"+rC+"-"+formula+"-"+isRange);
		XComponent xC = xDesk.getCurrentComponent();

		XModel xM = UnoRuntime.queryInterface(XModel.class,xC);

		XController xSpreadsheetController = xM.getCurrentController();
		
		XSpreadsheetView xSpreadsheetView = (XSpreadsheetView) UnoRuntime.queryInterface(XSpreadsheetView.class, xSpreadsheetController);

		XSpreadsheet xSpreadsheet = xSpreadsheetView.getActiveSheet();

		XCell xCell = xSpreadsheet.getCellRangeByName(rC).getCellByPosition(0, 0);
		String range = (isRange)?(":"):(";");
		xCell.setFormula("="+formula+"("+iC+range+fC+")"); 
	}
}