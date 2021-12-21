package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;

public class Printer extends JFrame implements Printable{
	protected void printFrame(){
	    PrinterJob printerJob = PrinterJob.getPrinterJob();
	    
	    printerJob.setPrintable(this);

	    // pop up a dialog box for the end user to fine tune the options.
	    if (printerJob.printDialog()) {
	        try {
	            // render the component onto the printer or print queue.
	            printerJob.print();
	        } catch ( PrinterException e ) {
	            System.out.println( "Error printing: " + e );
            }
        }
    }

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if ( pageIndex > 0 ){
			return Printable.NO_SUCH_PAGE;
        }
		// get the bounds of the component
        Dimension dim = this.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = pageFormat.getImageableHeight();
        double pWidth = pageFormat.getImageableWidth();

        double pXStart = pageFormat.getImageableX();
        double pYStart = pageFormat.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;
		        
        Graphics2D g2 = (Graphics2D) graphics;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, xRatio);
        this.paint(g2);
		return Printable.PAGE_EXISTS;
	}
}
