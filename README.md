# t24
T24 haber sitesi apisi ile geliştirilmiş bir uygulama, 
- ** kendimi geliştirmek için yaptım **

- İlk açılış ekranında 1 sn ’ye boyunca haber sitesinin logosu gösterilecektir.

- Son haberler
  * Bu bölümün haberleri slide edilebilecek şekilde gerçekleştirilmelidir
  * Bu bölümde kullanıcı son habere geldiğinde tekrar slide ederse ilk habere gitmelidir
  * Ekranda haberin diğer slide eden haberler arasında kaçıncı haber olduğunun kullanıcıya bildirilmesi gerekmektedir.
  * Slide eden haberler her 3 sn’ye de bir sonraki habere geçecek şekilde otomatik olarak değişebilmelidir.
  * Sayfa görünümü resmi
  * Başlık
          
- Diğer haberler
  * Bu bölümün haberleri liste şeklinde gerçekleştirilmelidir
  * Bu bölümde kullanıcı aşağıya scroll ettikçe
  * Listeye sıradan yeni haberler eklenmelidir.
  * Kullanıcı bir tuşa basmak zorunda kalmamalıdır.
  * Web servisinden çekilebilecek haber kalmadığında durmalıdır
  * Liste görünümü resmi
  * Başlık
  
- Refresh aksiyonu aşağıdaki gibi alınmalıdır.
  * Haberler listesi her 120 sn’ye de bir yenilenmelidir.
  * Haber yenilenmesi sırasında kullanıcının ekran içerisinde bulunduğu pozisyon kaybedilmemelidir.
  * Yenileme refresh layout ile sağlanmalıdır
  
- Haber kategorileri aşağıdaki bilgiler ile listelenecektir.
  * Kategori listesine action bar üzerinden tıklanarak erişilmelidir
  * Kategori action bar üzerinden tıklanıp, drop down menu şekilde açılmalıdır
  * Kategori haberleri listeleme
  * Kategori elemanına tıklandığında o kategoriye ilişkin haberler listelenmelidir
  * Kategori listesi sadece "Anasayfa Haberleri" sayfasında olmalıdır.
  
- İlk sayfa haberleri aşağıdaki bilgiler ile gösterilecektir.
  * Haber içeriği
  * Sayfa görünümü resmi
  * Başlık resmin üzerinde görüntülenmelidir.
  * Haber içeriği
  
- Kullanıcı uygulamadan Home tuşu ile çıkıp, uygulama ikonuna basarak uygulamayı tekrar açtığında kaldığı yerden devam edebilmelidir.
- Kullanıcı sağ/sol swipe gesture’ı ile o kategorideki diğer haberler arasında dolaşabilmelidir, view pager bulunmakta
- Haber Başlığı ve URL’i paylaşılabilmelidir
  * Haber paylaşımı ActionBar üzerinde bulunan mektup işareti ile yapılabilir
