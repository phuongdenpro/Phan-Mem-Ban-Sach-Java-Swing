package util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Placeholder {
	
	public void placeholder(JTextField txtField, String placeholder) {
		txtField.setForeground(Color.GRAY);
		txtField.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtField.getText().equals(placeholder)) {
		        	txtField.setText("");
		        	txtField.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtField.getText().isEmpty()) {
		        	txtField.setForeground(Color.GRAY);
		        	txtField.setText(placeholder);
		        }
		    }
	    });
	}
	
	public void placeholder(JPasswordField txtField, String placeholder) {
		txtField.setForeground(Color.GRAY);
		txtField.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txtField.getText().equals(placeholder)) {
		        	txtField.setText("");
		        	txtField.setEchoChar('*');
		        	txtField.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txtField.getText().isEmpty()) {
		        	txtField.setForeground(Color.GRAY);
		        	txtField.setEchoChar((char)0);
		        	txtField.setText(placeholder);
		        }
		    }
	    });
	}
}
