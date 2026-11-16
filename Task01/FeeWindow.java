import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class FeeWindow extends JFrame {
    private final JTextField regNo = new JTextField(14), amount = new JTextField(10);
    private final FeeService service = new FeeService();
    private final JTextArea status = new JTextArea(4, 28);
    public FeeWindow() {
        super("Student Fee Payment"); setLayout(new BorderLayout(8,8));
        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        form.add(new JLabel("Registration number")); form.add(regNo);
        form.add(new JLabel("Amount")); form.add(amount);
        JButton pay=new JButton("Pay"), clear=new JButton("Clear"), receipt=new JButton("Receipt");
        JPanel actions=new JPanel(new FlowLayout()); actions.add(pay); actions.add(clear); actions.add(receipt);
        status.setEditable(false); add(form,BorderLayout.NORTH); add(new JScrollPane(status),BorderLayout.CENTER); add(actions,BorderLayout.SOUTH);
        pay.addActionListener(e -> { try { var p=service.pay(regNo.getText(),new BigDecimal(amount.getText().trim())); status.setText("Payment recorded: "+p); }
            catch(IllegalArgumentException ex){ JOptionPane.showMessageDialog(this,ex.getMessage(),"Invalid payment",JOptionPane.ERROR_MESSAGE); }
            catch(Exception ex){ JOptionPane.showMessageDialog(this,"Payment could not be completed.","Payment error",JOptionPane.ERROR_MESSAGE); } });
        clear.addActionListener(e -> {regNo.setText(""); amount.setText(""); status.setText("");});
        receipt.addActionListener(e -> JOptionPane.showMessageDialog(this,status.getText().isBlank()?"No payment receipt is available.":status.getText(),"Receipt",JOptionPane.INFORMATION_MESSAGE));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); pack(); setLocationRelativeTo(null);
    }
    public static void main(String[] args){SwingUtilities.invokeLater(()->new FeeWindow().setVisible(true));}
}
class FeeService {
    record Payment(String registration, BigDecimal amount) {}
    Payment pay(String registration, BigDecimal amount) {
        String id=registration==null?"":registration.trim();
        if(!id.matches("[A-Za-z0-9-]{4,20}")) throw new IllegalArgumentException("Enter a valid registration number.");
        if(amount==null || amount.signum()<=0 || amount.scale()>2) throw new IllegalArgumentException("Amount must be positive with at most two decimal places.");
        // Replace this in-memory demonstration with parameterized JDBC in production.
        return new Payment(id,amount.setScale(2));
    }
}
