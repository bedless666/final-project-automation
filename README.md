# Final Project Automation

**📌 Deskripsi Proyek**

Final Project Automation adalah framework pengujian otomatis yang menggabungkan **Web UI Testing** dan **API Testing** dalam satu repository. Framework ini menggunakan **Java, Gradle, Cucumber, Selenium, dan Rest Assured**, serta mendukung eksekusi test otomatis melalui **GitHub Actions**.

**📂 Struktur Folder**

```bash
final-project-automation/
│── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── api/          # Folder untuk API testing
│   │   │   │   ├── stepdefinitions/  # Step definitions untuk API
│   │   │   │   ├── runners/          # Runner untuk API test
│   │   │   │   ├── utils/            # Utils/helper untuk API
│   │   │   ├── web/          # Folder untuk Web UI testing
│   │   │   │   ├── stepdefinitions/  # Step definitions untuk Web UI
│   │   │   │   ├── pages/            # Page Object Model (POM) untuk Web UI
│   │   │   │   ├── runners/          # Runner untuk Web UI test
│   │   │   │   ├── utils/            # Utils/helper untuk Web UI
│   │   ├── resources/
│   │   │   ├── features/      # Folder untuk feature files (Gherkin)
│   │   │   │   ├── api/       # Feature files untuk API
│   │   │   │   ├── web/       # Feature files untuk Web UI
│── .github/workflows/ci.yml   # GitHub Actions workflow
│── build.gradle               # Konfigurasi Gradle
│── README.md                  # Dokumentasi project
│── .gitignore                  # File yang perlu di-ignore oleh Git
```

**🚀 Cara Menjalankan Test**

**1. Clone Repository**

```bash
git clone https://github.com/username/final-project-automation.git
cd final-project-automation
```

**2. Jalankan API Test**

```bash
./gradlew testApi
```

**3. Jalankan Web UI Test**

```bash
./gradlew testWeb
```

**4. Melihat Hasil Report**

Setelah test selesai, hasil laporan dapat ditemukan di:

```bash
build/reports/tests/testApi/index.html
build/reports/tests/testWeb/index.html
```

**🔄 Integrasi GitHub Actions**

Workflow otomatis dijalankan pada:

- **Push ke branch ****`main`**
- **Pull Request ke ****`main`**
- **Manual Trigger dari tab Actions**

File konfigurasi berada di **`.github/workflows/ci.yml`**.

**📦 Dependencies**

- **Java 17**
- **Gradle**
- **Cucumber (cucumber-java, cucumber-junit)**
- **Selenium (selenium-java, webdriver-manager)**
- **Rest Assured (API Testing)**
- **JUnit 4**

**📄 Lisensi**

Proyek ini dibuat untuk tujuan pembelajaran dan pengembangan keterampilan dalam pengujian otomatis.

---

🎯 **Sekarang framework ini siap digunakan!** 🚀

