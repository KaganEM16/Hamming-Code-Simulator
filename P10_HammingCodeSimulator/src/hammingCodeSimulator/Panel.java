package hammingCodeSimulator;

import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Panel extends JFrame implements ActionListener
{
	JLabel[] labels;
	JButton[] buttons;
	JTextField[] texts;
	ArrayList<String> memory = new ArrayList<>();
	
	public Panel() {
	    super("Hamming SEC-DED Code Simulator");
	    int en = 800, boy = 770;
	    this.setSize(en, boy);
	    this.setLayout(null);
	    this.getContentPane().setBackground(Color.LIGHT_GRAY);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setResizable(false);
	    
	    labels = new JLabel[4];
	    // Başlık
	    labels[0] = new JLabel("Hamming SEC-DED Code Simulator");
	    labels[0].setBounds(20, 20, 700, 100);
	    // 2. Satır
	    labels[1] = new JLabel("Lütfen 8, 16 veya 32 Bitlik Bir Veri Girişi Yapınız: ");
	    labels[1].setBounds(0, 120, 600, 100);
	    // 4. Satır
	    labels[2] = new JLabel("Oluşturulan Hamming Code Burada Görünecektir.");
	    labels[2].setBounds(100, 300, 600, 100);
	    labels[2].setOpaque(true);
	    labels[2].setBackground(Color.DARK_GRAY);
	    // 6. Satır
	    labels[3] = new JLabel("Manuel Olarak Hata Eklemek İstediğiniz Bit Pozisyonunu Giriniz:");
	    labels[3].setBounds(20, 480, 650, 100);
	    for(int i=0 ; i<labels.length ; i++) {
	    	setLabel(labels[i], (i==0)?Color.BLUE: (i==2)? Color.WHITE:Color.BLACK, (i==0)? 30:17);
	    }
	    
	    // 2 ve 6. Satırlardaki Text Alanları:
	    texts = new JTextField[2];
	    for(int i=0 ; i<texts.length ; i++) {
	    	texts[i] = new JTextField();
	    	texts[i].setBounds((i==0)? 530:655, (i==0)? 150:510, (i==0)? 210:60, 40);
	    	texts[i].setFont(new Font("Verdana", Font.PLAIN, 18));
	    }
	    
	    buttons = new JButton[6];
	    String[] butonlaraYazılacaklar = {"Hamming Code Oluştur","Rastgele Tek Hata Ekle","Rastgele Çift Hata Ekle"
	    		, "Hatayı Tespit Et", "Memory'i Görüntüle", "Ekle"};
	    for(int i=0 ; i<buttons.length ; i++) {
	    	buttons[i] = new JButton(butonlaraYazılacaklar[i]);
	    	buttons[i].setBackground(Color.BLUE);
	    	buttons[i].setForeground(Color.WHITE);
	    	buttons[i].setFont(new Font("Verdana",Font.BOLD ,(i==0 || i==3)? 20:15));
	    	buttons[i].addActionListener(this);
	    }
	    // 3. Satır
	    buttons[0].setBounds(100, 220, 600, 70);
	    // 5. Satır
	    buttons[1].setBounds(10, 420, 250, 70);
	    buttons[2].setBounds(270, 420, 250, 70);	    
	    // 8. Satır
	    buttons[3].setBounds(100, 620, 600, 70);
	    // 5. Satır
	    buttons[4].setBounds(530, 420, 250, 70);
	    // 7. Satır
	    buttons[5].setBounds(360, 560, 80, 40);
	    
//	    Başlık
	    this.add(labels[0]);
//	    2. Satır
	    this.add(labels[1]);
	    this.add(texts[0]);
//	    3. Satır
	    this.add(buttons[0]);
//	    4. Satır
	    this.add(labels[2]);
//	    5. Satır
	    this.add(buttons[1]);
	    this.add(buttons[2]);
	    this.add(buttons[4]);
//	    6. Satır
	    this.add(labels[3]);
	    this.add(texts[1]);
//	    7. Satır
	    this.add(buttons[5]);
//	    8. Satır
	    this.add(buttons[3]);
	    	 
	    this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton basilanButon = (JButton)e.getSource();
		
		if(basilanButon == buttons[0]) 
		{
//			Hamming Code Oluştur Butonu: texts[0}'a girilen veri üzerinden hamming code oluşturulması
			
			String input = texts[0].getText().trim();
			
			if (!input.matches("[01]+")) {
				JOptionPane.showMessageDialog(this, "Yalnızca 0 ve 1'lerden oluşan bir veri girmelisiniz.");
				texts[0].setText("");
				return;
			}
			
			int len;
			int m = input.length();
			if (m != 8 && m != 16 && m != 32) {
				JOptionPane.showMessageDialog(this, "Yalnızca 8, 16 veya 32 bitlik bir veri girmelisiniz.");
				texts[0].setText("");
				return;
			}
			
			memory.add(input);
			// Parity biti sayısının belirlenmesi
			int r = (int) Math.ceil(Math.log(m + 1) / Math.log(2));
			len = m + r;
						
			char[] dataBits = new StringBuilder(input).toString().toCharArray();
			char[] hamming = new char[len];			
			
			// Veri bitlerini yerleştir
			int dataIndex = 0;
			for (int i = 0; i < len; i++) {
			    // Eğer i+1 bir ikinin kuvveti değilse veri biti yerleştir
			    if (!isPowerOfTwo(i + 1)) {
			        hamming[i] = dataBits[dataIndex];
			        dataIndex++;
			    }
			}
			// Eğer i+1 bir ikinin kuvveti ise parity bitini hesapla
			for (int i = 0; i < hamming.length; i++) {
	            if (isPowerOfTwo(i + 1)) { // Parity biti pozisyonu ise
	                hamming[i] = calculateParity(hamming, i + 1);
	            }
	        }			
			
			labels[2].setText(reverse(hamming));
		} 
		else if(basilanButon == buttons[1])
		{
//			Rastgele Tek Hata Ekle Butonu: labels[2]'de yer alan Hamming kodun rastgele tek bitinin değiştirilmesi
			
			String hamming = labels[2].getText();
			
			if(hamming == "Oluşturulan Hamming Code Burada Görünecektir.") {
				JOptionPane.showMessageDialog(this, "Öncelikle geçerli bir hamming kodu oluşturmalısınız.");
				texts[0].setText("");
				texts[1].setText("");
				return;
			}
			char[] arr = hamming.toCharArray();
			int len = hamming.length();			
			int index = (int)(Math.random()*len);
						
			try {
				int a = Character.getNumericValue(hamming.charAt(index));;
				if(a==0) {
					arr[index] = '1';
				}else {
					arr[index] = '0';
				}
				JOptionPane.showMessageDialog(this, "İndex " + index + "'deki " + a + " biti başarıyla değiştirildi.");
				labels[2].setText(String.valueOf(arr));
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Hata! Lütfen tekrar deneyiniz.");
				texts[0].setText("");
				texts[1].setText("");
			}
		} 
		else if(basilanButon == buttons[2]) 
		{
//			Rastgele Çift Hata Ekle Butonu: labels[2]'de yer alan Hamming kodun rastgele iki bitinin değiştirilmesi
			
			String hamming = labels[2].getText();
			
			if(hamming == "Oluşturulan Hamming Code Burada Görünecektir.") {
				JOptionPane.showMessageDialog(this, "Öncelikle geçerli bir hamming kodu oluşturmalısınız.");
				texts[0].setText("");
				texts[1].setText("");
				return;
			}
			char[] arr = hamming.toCharArray();
			int len = hamming.length();			
			int index1 = (int)(Math.random()*len);
			int index2 = (int)(Math.random()*len);
			
			if(index1 == index2) 
				index2 = (index2>0)? index1 - 1: index2 + 1;
			
			try {
				int a = Character.getNumericValue(hamming.charAt(index1));
				int b = Character.getNumericValue(hamming.charAt(index2));
				if(a==0) {
					arr[index1] = '1';
				}else {
					arr[index1] = '0';
				}
				if(b==0) {
					arr[index2] = '1';
				}else {
					arr[index2] = '0';
				}
				JOptionPane.showMessageDialog(this, "İndex " + index1 + "'deki " + a + " biti ve " + index2 + "'deki " + b + " biti başarıyla değiştirildi.");
				labels[2].setText(String.valueOf(arr));
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Hata! Lütfen tekrar deneyiniz.");
				texts[0].setText("");
				texts[1].setText("");
			}
		} 
		else if (basilanButon == buttons[3]) 
		{
		// Hatayı Tespit Et Butonu: labels[2]'de yer alan hamming koda göre hatanın belirlenmesi

			String hamming = labels[2].getText().trim();

			if (hamming.equals("Oluşturulan Hamming Code Burada Görünecektir.") || !hamming.matches("[01]+")) {
				JOptionPane.showMessageDialog(this, "Öncelikle geçerli bir Hamming kodu oluşturmalısınız.");
				texts[0].setText("");
				texts[1].setText("");
				return;
			}

			// Hamming kodunu ters çeviriyoruz çünkü parity kontrolleri pozisyonlara göre yapılacak
			hamming = reverse(hamming.toCharArray());
			char[] arr = hamming.toCharArray();
			int len = arr.length;

			int check = (int) Math.ceil(Math.log(len + 1) / Math.log(2));
			int[] checkBits = new int[check];
			
			for (int i = 0; i < check; i++) {
				int parity = 0;
				
				for (int j = 1; j <= len; j++) {
					if (((j >> i) & 1) == 1) {
						parity ^= (arr[j - 1] - '0');
					}
				}
				checkBits[i] = parity;
			}

			// Hatalı pozisyonun hesaplanması
			int errorPos = 0;
			for (int i = 0; i < checkBits.length; i++) {
				if (checkBits[i] == 1) {
					errorPos += Math.pow(2, i);
				}
			}

			if (errorPos == 0) {
				JOptionPane.showMessageDialog(this, "Hata bulunamadı. Hamming kodu doğru.");
			} else {
				int gerçekPozisyon = len - errorPos + 1;				
				// Hatalı bitin düzeltilmesi
				arr[errorPos - 1] = (arr[errorPos - 1] == '0') ? '1' : '0';
				String düzeltilmiş = reverse(new String(arr).toCharArray());
				labels[2].setText(düzeltilmiş);
				JOptionPane.showMessageDialog(this, "Hatalı bit " + (gerçekPozisyon-1) + ". indexte bulundu ve düzeltildi.");
			}
		}
 
		else if(basilanButon == buttons[4])
		{
//			Memory'i Görüntüle: Daha önceden girilen verilerin gösterilmesi
			if (memory.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Bellekte kayıtlı veri yok.");
	        } else {
	            StringBuilder sb = new StringBuilder("Bellekte kayıtlı veriler:\n");
	            for (int i = 0; i < memory.size(); i++) {
	                sb.append((i + 1) + "-) " + memory.get(i) + "\n");
	            }
	            JOptionPane.showMessageDialog(this, sb.toString());
	            texts[0].setText("");
	        }
		}
		else
		{
//			Manuel Olarak Hata Ekleme Butonu:
			String hamming = labels[2].getText();
			if(hamming == "Oluşturulan Hamming Code Burada Görünecektir.") {
				JOptionPane.showMessageDialog(this, "Öncelikle geçerli bir hamming kodu oluşturmalısınız.");
				texts[0].setText("");
				texts[1].setText("");
				return;
			}
			char[] arr = hamming.toCharArray();
			
			try {
				int index = Integer.valueOf(texts[1].getText());
				int a = Character.getNumericValue(hamming.charAt(index));
				if(a==0) {
					arr[index] = '1';
				}else {
					arr[index] = '0';
				}
				labels[2].setText(String.valueOf(arr));
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "Hata! Lütfen tekrar deneyiniz.");				
				texts[1].setText("");
			}
		}
	}
	
	private String reverse(char[] hamming) {
		return new StringBuilder(new String(hamming)).reverse().toString();
	}

	private boolean isPowerOfTwo(int n) {
	    return (n & (n - 1)) == 0;
	}
		
//	LSB Öncelikli hesaplama yapılır
	private char calculateParity(char[] hamming, int parityPosition) {
	    int parity = 0;
	    for (int i = parityPosition - 1; i < hamming.length; i += 2 * parityPosition) {
	        for (int j = i; j < Math.min(i + parityPosition, hamming.length); j++) {
	            if (hamming[j] == '1') {
	                parity ^= 1;
	            }
	        }
	    }
	    return (parity == 1) ? '1' : '0';
	}	
	
	public void setLabel(JLabel label, Color renk, int boyut)
	{
		label.setForeground(renk);
		label.setFont(new Font("Verdana",Font.BOLD ,boyut));
		label.setHorizontalAlignment(0);
	}
}
