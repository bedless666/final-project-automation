# 🚀 Automation Test Framework

## 📌 Overview

Repository ini merupakan **Automation Test Framework** yang mengintegrasikan **Web UI Testing** dan **API Testing** dalam satu proyek. Framework ini dibangun menggunakan **Java**, **Gradle**, **Cucumber**, **Selenium**, dan **Rest Assured**, serta didukung oleh **GitHub Actions** untuk menjalankan pengujian secara otomatis.

## 📂 Project Structure

```
src
├── main
│   ├── java
│   │   ├── com/automation/Main.java
├── test
│   ├── java
│   │   ├── api
│   │   │   ├── runners/ApiTestRunner.java
│   │   │   ├── stepdefinitions/ApiStepDefinitions.java
│   │   ├── web
│   │   │   ├── pages/CartPage.java
│   │   │   ├── pages/LoginPage.java
│   │   │   ├── runners/WebTestRunner.java
│   │   │   ├── stepdefinitions/CartStepDefinitions.java
│   │   │   ├── stepdefinitions/LoginStepDefinitions.java
│   │   │   ├── utils/Hooks.java
│   │   ├── TestChrome.java
│   ├── resources
│   │   ├── features
│   │   │   ├── api/sample.feature
│   │   │   ├── web/login.feature
│
build.gradle
ci.yml
README.md
```

## 🛠️ Tools & Libraries

- **Java 17** - Bahasa pemrograman utama
- **Gradle** - Build automation tool
- **Cucumber** - Gherkin-based testing framework
- **Selenium WebDriver** - Web UI automation
- **Rest Assured** - API testing
- **Allure & Cucumber Reports** - Test reporting
- **GitHub Actions** - CI/CD automation

## 🚀 Installation & Setup

### **1️⃣ Clone Repository**

```sh
git clone https://github.com/username/repository.git
cd repository
```

### **2️⃣ Install Dependencies**

```sh
./gradlew clean build
```

### **3️⃣ Menjalankan Test Secara Lokal**

#### **Menjalankan Test keseluruhuan**

```sh
./gradlew test
```

#### **Menjalankan API Test**

```sh
./gradlew testApi
```

#### **Menjalankan Web UI Test**

```sh
./gradlew testWeb
```

## ✅ Test Execution

### **Menjalankan Test di GitHub Actions**

Framework ini sudah terintegrasi dengan **GitHub Actions**. Test akan berjalan otomatis ketika:

- Ada **push ke branch **``
- Ada **pull request ke **``
- Dijalankan secara manual melalui **workflow dispatch**

#### **Melihat Hasil Test**

1. Buka tab **Actions** di repository GitHub
2. Pilih workflow yang dijalankan
3. Download laporan dari **Artifacts**

## 📊 Reporting

- **Cucumber Report** tersedia dalam format **HTML** dan **JSON**
- Report otomatis diunggah ke **GitHub Actions Artifacts** setelah test selesai
- Report bisa ditemukan di:
    - `build/reports/cucumber/cucumber-test.html`
    - `build/reports/cucumber/testapi/cucumber-api.html`
    - `build/reports/cucumber/testweb/cucumber-web.html`

## 🖥️ Web UI Test Scenarios

✅ **Website yang diuji:** [DemoBlaze](https://www.demoblaze.com/)

| Scenario                                 | Type       |
| ---------------------------------------- | ---------- |
| Login dengan kredensial benar            | Positive   |
| Login dengan kredensial salah            | Negative   |
| Login tanpa memasukkan username/password | Negative   |
| End-to-End login dan navigasi ke cart    | End-to-End |
| Logout setelah login                     | Positive   |

## 🔗 API Test Scenarios

✅ **API yang diuji:** [DummyAPI](https://dummyapi.io/docs/)

| Endpoint       | Method | Scenario                       |
| -------------- | ------ | ------------------------------ |
| `/user/{id}`   | GET    | Ambil data user berdasarkan ID |
| `/user/create` | POST   | Buat user baru                 |
| `/user/{id}`   | PUT    | Update data user               |
| `/user/{id}`   | DELETE | Hapus user                     |
| `/tag`         | GET    | Ambil daftar tag               |

## 👥 Contributors

- **Bharata Aryaseta** ([GitHub Profile](https://github.com/bedless666/))

---

🎯 **Framework ini dibuat sebagai bagian dari Final Project Jayjay. 🚀**

