# Hamming SEC-DED Code Simulator With Java

Bu proje, **BLM230 Bilgisayar Mimarisi** dersi kapsamında geliştirilmiş bir **Hamming SEC-DED (Single Error Correcting, Double Error Detecting) kod simülatörüdür**. Proje, Java Swing kullanılarak görsel kullanıcı arayüzü ile oluşturulmuştur.

## 🔧 Özellikler

- Kullanıcıdan 8, 16 veya 32 bitlik veri girişi alır  
- Girilen veriye Hamming SEC-DED algoritması uygulanarak hata düzeltme ve tespit kodu üretir  
- Simülasyon arayüzü üzerinden yapay olarak bit hatası oluşturulabilir  
- Hata analizi sonrası sendrom kelimesi üretilir ve hatalı bit tespit edilerek düzeltme yapılır  
- Basit ve anlaşılır bir kullanıcı arayüzü sunar  

## 📸 Arayüz Görseli

<img src="ProjeResimleri/Resim 1.png" width="500" alt="Resim 1 Açıklaması">
<img src="ProjeResimleri/Resim 2.png" width="500" alt="Resim 2 Açıklaması">
<img src="ProjeResimleri/Resim 3.png" width="500" alt="Resim 3 Açıklaması">

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler

- Java JDK 17 veya üzeri

### Derleme Adımları

1. `Panel.java` ve `Test.java` dosyalarını aynı klasöre yerleştirin  
2. Komut satırında ilgili klasöre geçin  
3. Aşağıdaki komutu girerek derleyin:  
   `javac Panel.java Test.java`  
4. Ardından şu komutla çalıştırın:  
   `java Test`  

## 📂 Dosya Yapısı

- `Panel.java`: Swing arayüzünü içeren sınıf  
- `Test.java`: Programın main fonksiyonunu çalıştıran sınıf  
- `README.md`: Projeyi açıklayan bu dosya  

## 📘 Proje Açıklaması (Özet)

* Simülatör, belleğe yazılacak veriler için Hamming kodunu hesaplayarak saklamayı ve hatalı bitleri bulup düzelterek verinin güvenilirliğini sağlamayı hedefler. Kullanıcı dostu bir arayüz aracılığıyla bu işlemler görsel olarak deneyimlenebilir.
* [Projenin Detaylı Dokümantasyonuna Bu Linkten Ulaşabilirsiniz] (Daha Sonra Eklenecektir.)

## 🎥 Demo Videosu

Demo videosunu izlemek için şu bağlantıyı kullanabilirsiniz:  
[Tanıtım Videosu Bağlantısı](https://youtu.be/J0DcX5IPUTA)
