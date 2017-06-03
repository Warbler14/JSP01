package controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 */
@WebServlet("/getPage")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String pageList[] = {"/HelloWorld"
		
		,"/ImageGet?fileName=overcome.jpg"
		,"/ImageGetSize?width=100&height=100"
		,"/paintImage?number=5"
		,"/drawSingleLine?x1=50&y1=250&x2=450&y2=250"
		,"/drawSpiral?width=500&height=500&x1=250&y1=250&degree=10&radius=50&count=50"
		
		,"/drawQuadrangle?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=400&y3=100&x4=100&y4=100"
		,"/drawTriangle?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=250&y3=120"
		,"/drawXline?width=500&height=500&x1=250&y1=250&x2=50&y2=80"
		,"/drawMirrorLine?width=500&height=500&x1=250&y1=250&x2=80&y2=300&x3=200&y3=50"
		,"/drawMiddleDot?width=500&height=500&x1=50&y1=450&x2=450&y2=50&radius=50"
		
		,"/drawBoxing01?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=250&y3=120"
		,"/drawTwoBox?width=500&height=500&x1=100&y1=100&x2=200&y2=200"
		,"/drawOctagon?width=500&height=500&length=100"
		,"/drawSymbol?cmd=inputPage"
		,"/drawSymbol2?cmd=inputPage"
		
		,"/drawMousePosition?cmd=inputPage"
		,"/drawAjaxImage?cmd=inputPage"
		,"/drawText?cmd=inputPage"
		,"/drawRandomColor?cmd=inputPage"
		,"/drawSpiral2?width=500&height=500&round=20"
		
		,"/drawDiamond?width=500&height=500&round=20"
		,"/drawRotateSymbol?cmd=inputPage"
		,"/drawDropBox?cmd=inputPage"
		,"/drawMultiDropBox?cmd=inputPage"
		,"/drawCircleBox?cmd=inputPage"
				
		,"/drawColorMatrix?cmd=inputPage"
		,"/drawThrowBox?cmd=inputPage"
		,"/drawBounceBox?cmd=inputPage"
		,"/drawPendulum?cmd=inputPage"
	};
	
	static Logger logger = Logger.getLogger(HelloWorld.class);
       
  
    public PageController() {
        super();
        
    }
    
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<?xml version = \"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<data>");
		pw.println("<listSize>" + pageList.length + "</listSize>");
		for(int i = 0, ii = pageList.length ; i<ii ; i++ ){
			pw.println("<pageList><![CDATA[" + pageList[i] + "]]></pageList>");
		}
		pw.println("</data>");
		pw.close();
		
	}

}
