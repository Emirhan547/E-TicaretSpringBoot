# 📦 E-Ticaret Sistemi

Bu proje, **Spring Boot** kullanılarak geliştirilmiş, çok katmanlı ve modüler bir e-ticaret sistemidir. Kullanıcı yönetimi, ürün katalog yönetimi, sipariş takibi, ödeme işlemleri ve yorum sistemini içeren geniş kapsamlı bir altyapıya sahiptir.

## 📌 Özellikler

- **Kullanıcı Yönetimi**:
  - JWT tabanlı güvenli kimlik doğrulama
  - Roller (ADMIN, USER, MANAGER)
  - Şifre hashleme (BCrypt kullanımı)
  - Kullanıcı e-posta doğrulaması
- **Ürün Yönetimi**:
  - Ürün ekleme, güncelleme, silme ve listeleme
  - Ürün kategorileri
  - Stok yönetimi
  - Ürün görselleri
- **Sipariş Yönetimi**:
  - Sipariş oluşturma ve durum güncelleme (Pending, Processing, Shipped, Delivered, Canceled)
  - Sipariş takibi ve iptali
  - Kullanıcıya sipariş bildirimleri
- **Ödeme Sistemi**:
  - Kredi kartı, banka havalesi, kapıda ödeme seçenekleri
  - Gerçek ödeme entegrasyonu (Stripe veya PayPal desteği eklenebilir)
  - Ödeme durumları ve hata yönetimi
- **Kupon & Kampanya Yönetimi**:
  - Özel indirim kuponları
  - Minimum alışveriş tutarı gerekliliği
  - Kupon kodu doğrulama ve kullanma
- **Yorum Sistemi**:
  - Kullanıcıların ürünlere yorum yapabilmesi ve puan verebilmesi
  - Spam ve uygunsuz yorumları filtreleme
- **Sepet & Wishlist**:
  - Kullanıcıların favori ürünlerini listeleyebilmesi
  - Sepete ürün ekleme ve çıkarma
- **Performans & Güvenlik**:
  - Önbellekleme (Spring Cache, Redis entegrasyonu opsiyonel)
  - Global Exception Handling (Özel hata yönetimi mekanizması)
  - RESTful API standartlarına uygunluk

---

## 🚀 Kullanılan Teknolojiler

- **Backend:** Java, Spring Boot, Hibernate, Spring Security, Spring Data JPA
- **Database:** Oracle
- **Önbellekleme:** Spring Cache (Redis entegrasyonu opsiyonel)
- **DTO & Mapping:** MapStruct
- **API Güvenliği:** JWT (JSON Web Token)
- **Asenkron İşlemler:** Spring Async
- **Loglama:** SLF4J, Logback
- **CI/CD:** GitHub Actions ile entegrasyon (opsiyonel)

---

## 🏗 Katmanlı Mimari Açıklaması

Bu proje **çok katmanlı bir mimariye** sahiptir. Katmanların işlevleri aşağıdaki gibidir:

- **Controller Katmanı**: API uç noktalarını barındırır ve gelen istekleri işler.
- **Service Katmanı**: İş kurallarını uygular ve domain mantığını yönetir.
- **Repository Katmanı**: Veritabanı işlemlerini yönetir (Spring Data JPA kullanılarak).
- **Entity Katmanı**: Hibernate aracılığıyla veritabanı nesnelerini tanımlar.
- **DTO Katmanı**: Veri transferi için kullanılır, istemciye dönen veya istemciden gelen verileri taşır.
- **Mapper Katmanı**: Entity ve DTO dönüşümlerini yönetir.

---

## 📡 API Kullanımı

### 🏷️ Kupon İşlemleri

#### 1️⃣ Kupon Oluşturma

```http
POST /api/coupons
```

Body:

```json
{
  "code": "DISCOUNT50",
  "discountAmount": 50,
  "expirationDate": "2025-12-31"
}
```

#### 2️⃣ Kupon Kodu ile Bilgi Alma

```http
GET /api/coupons/{code}
```

#### 3️⃣ Kupon Silme

```http
DELETE /api/coupons/{id}
```

---

### 💳 Ödeme İşlemleri

#### 1️⃣ Ödeme İşleme

```http
POST /api/payments/process
```

Body:

```json
{
  "orderId": 123,
  "amount": 250.75,
  "paymentMethod": "CREDIT_CARD"
}
```

---

README'yi, projenin **servis katmanındaki iş mantığını, ödeme ve kupon yönetimi gibi özellikleri, Redis önbellekleme, asenkron işlemler ve global hata yönetimi** gibi önemli detaylarla genişlettim. Eğer daha fazla ekleme yapmak istersen bana bildirebilirsin! 🚀

