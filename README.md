

# 🏛️ E-Help Desk - Legal Assistance Desktop Application

> A comprehensive desktop solution providing legal assistance, document management, and government services access for citizens with AI-powered guidance.



***

## 🌟 Overview

E-Help Desk is a powerful desktop application that bridges the gap between citizens and legal services. With seamless integration of AI assistance, cloud document storage, and government service portals, it empowers users with instant access to legal guidance and administrative processes.

## ✨ Key Features

<table>
<tr>
<td width="50%">

### 🔐 **Secure Authentication**
- Firebase-powered user management
- Role-based access control
- Admin dashboard with analytics
- Password validation & security

</td>
<td width="50%">

### 📄 **Smart Document Management**
- Cloud storage with Firebase
- Real-time upload/download
- Document verification system
- File format validation

</td>
</tr>
<tr>
<td width="50%">

### 🤖 **AI Legal Assistant**
- Gemini AI-powered chat bot
- Contextual legal guidance
- Note-taking system
- 24/7 availability

</td>
<td width="50%">

### 🏛️ **Government Services**
- Land records search
- Property registration
- RTI application filing
- Court procedure guidance

</td>
</tr>
</table>

***

## 📸 Application Screenshots

<details>
<summary>🖼️ Click to view screenshots</summary>

| Login & Authentication | Dashboard & Navigation |
|:---------------------:|:---------------------:|
| <img src="https://github.com/user-attachments/assets/a52c48c6-0ad5-427a-9213-a08c03def11a" width="400"/> <img width="400" alt="Screenshot 2025-08-28 175600" src="https://github.com/user-attachments/assets/618fe2d5-542f-4c80-b676-34fc6cd4c19a" /> | <img src="https://github.com/user-attachments/assets/135d04db-2012-49a2-9d97-9d5a2986ff72" width="400"/> |

| AI Legal Assistant | Document Management |
|:-----------------:|:------------------:|
| <img src="https://github.com/user-attachments/assets/67f73cf9-c4f1-4209-9714-b65b15064788" width="400"/> | <img src="https://github.com/user-attachments/assets/270a41f2-731d-460c-9c8f-d4075b5c4a53" width="400"/> |

| Government Services | Admin Panel |
|:------------------:|:-----------:|
| <img src="https://github.com/user-attachments/assets/fbd8cfca-dd28-4ecb-ad18-22c54fa926a5" width="400"/> | <img src="https://github.com/user-attachments/assets/cef93d21-f094-4368-97f4-254d24c8e214" width="400"/> |

</details>

***

## 🚀 Quick Start

### Prerequisites

| Requirement | Version | Download Link |
| :-- | :-- | :-- |
| **Java JDK** | 11+ | [AdoptOpenJDK](https://adoptopenjdk.net/) |
| **JavaFX** | 11+ | [OpenJFX](https://openjfx.io/) |
| **Maven** | 3.6+ | [Apache Maven](https://maven.apache.org/) |
| **Firebase Project** | Latest | [Firebase Console](https://console.firebase.google.com/) |

### Installation Steps

```bash
# 1. Clone the repository
git clone https://github.com/bhagwatlipne2004/e-help_desk
cd e-help-desk

# 2. Install dependencies
mvn clean install

# 3. Configure Firebase (see Firebase Setup section)

# 4. Run the application
mvn javafx:run
```


***

## 🔥 Firebase Configuration

### 📋 Setup Checklist

- [ ] Create Firebase project
- [ ] Enable Authentication (Email/Password)
- [ ] Setup Firestore Database
- [ ] Configure Firebase Storage
- [ ] Generate service account key
- [ ] Update API keys in code


### 🔧 Detailed Setup

<details>
<summary>📖 Click for detailed Firebase setup instructions</summary>

#### 1. Create Firebase Project
1. Visit [Firebase Console](https://console.firebase.google.com/)
2. Click "Add project" → Follow setup wizard
3. Enable Authentication, Firestore, and Storage

#### 2. Authentication Setup
```
Firebase Console → Authentication → Sign-in method → Enable Email/Password
```

#### 3. Firestore Collections
Create these collections in Firestore:
```
📁 users/          # User profiles and data
📁 notes/          # AI assistant conversation notes
📁 documents/      # Document metadata
```

#### 4. Service Account Key
```
Project Settings → Service Accounts → Generate new private key
```
**⚠️ Important**: Rename to `login-signup-971fe-firebase-adminsdk-fbsvc-9821d104be.json` and place in `src/main/resources/`

#### 5. Update Configuration
```java
// AuthController.java
public final static String API_KEY = "YOUR_FIREBASE_WEB_API_KEY";

// AiApiController.java  
String c2w_ai_apiKey = "YOUR_GEMINI_AI_API_KEY";

// firebasesdk.java
String fileName = "YOUR_FIREBASE_SERVICE_ACCOUNT_FILE.json";
```

</details>

***

## 📁 Project Architecture

```
📦 e-help-desk/
├── 📂 src/main/java/com/superx/
│   ├── 📂 Controller/
│   │   ├── 📂 AI/
│   │   │   ├── 🤖 AiApiController.java
│   │   │   ├── 📝 NotesController.java
│   │   │   └── 🎨 FormatController.java
│   │   ├── 🔐 AuthController.java
│   │   ├── 👤 LoginController.java
│   │   ├── ✍️ SignupController.java
│   │   ├── 👑 AdminLoginController.java
│   │   ├── 👁️ ViewController.java
│   │   └── 🔥 firebasesdk.java
│   └── 📂 view/
│       ├── 🤖 AI/              # AI chat interface
│       ├── 📄 Document/        # Document management
│       ├── 🔑 Loginpages/      # Authentication UI
│       ├── 👑 admin/           # Admin dashboard
│       ├── 🏞️ landRecords/     # Land records portal
│       └── ⚖️ legalTech/       # Legal services
├── 📂 src/main/resources/
│   ├── 🖼️ images/             # Application assets
│   └── 🔑 firebase-config.json # (Not included - add yours)
├── 📋 pom.xml
├── 📖 README.md
└── 📜 LICENSE
```


***

## 🛠️ Dependencies

<details>
<summary>📦 View complete dependency list</summary>

```xml
<dependencies>
    <!-- JavaFX Core -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>11.0.2</version>
    </dependency>
    
    <!-- Firebase Integration -->
    <dependency>
        <groupId>com.google.firebase</groupId>
        <artifactId>firebase-admin</artifactId>
        <version>8.1.0</version>
    </dependency>
    
    <!-- Rich Text Support for AI Chat -->
    <dependency>
        <groupId>org.fxmisc.richtext</groupId>
        <artifactId>richtextfx</artifactId>
        <version>0.10.7</version>
    </dependency>
    
    <!-- Excel Export Functionality -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.2</version>
    </dependency>
    
    <!-- JSON Processing -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version>
    </dependency>
</dependencies>
```

</details>

***

## 👥 User Guide

### 🔰 For Citizens

| Feature | Description | How to Use |
| :-- | :-- | :-- |
| **Register** | Create your account | Email → Password → Verify |
| **Upload Documents** | Store legal documents securely | Dashboard → Documents → Upload |
| **AI Assistant** | Get instant legal guidance | Chat → Ask questions → Save notes |
| **Government Services** | Access various portals | Services → Select category |

### 👑 For Administrators

| Feature | Description | Access Method |
| :-- | :-- | :-- |
| **User Management** | View and manage all users | Admin Panel → Users |
| **Document Verification** | Approve/reject documents | Admin Panel → Documents |
| **Analytics** | System usage statistics | Admin Panel → Dashboard |
| **Data Export** | Export user data to Excel | Admin Panel → Export |


***

## 🔧 Troubleshooting

<details>
<summary>🚨 Common Issues & Solutions</summary>

### Firebase Connection Issues
```
❌ Problem: "Firebase initialization failed"
✅ Solution: 
- Check service account JSON file location
- Verify internet connection
- Ensure Firebase project is active
```

### JavaFX Module Errors
```
❌ Problem: "Module javafx.controls not found"
✅ Solution:
--module-path /path/to/javafx/lib 
--add-modules javafx.controls,javafx.fxml
```

### Authentication Problems
```
❌ Problem: "Invalid API key"
✅ Solution:
- Verify API key in AuthController.java
- Check Firebase project settings
- Ensure authentication is enabled
```

### Document Upload Failures
```
❌ Problem: "Upload failed"
✅ Solution:
- Check file size (max 10MB)
- Verify file format (PDF, DOC, JPG, PNG)
- Ensure Firebase Storage is configured
```

</details>

***

## 🤝 Contributing

We welcome contributions from the community! Here's how you can help:

### 🚀 Getting Started

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### 📝 Contribution Guidelines

- Follow Java naming conventions
- Add comprehensive JavaDoc comments
- Write unit tests for new features
- Ensure Firebase security best practices
- Update documentation for new features

***

## 📊 Roadmap

- [ ] **Mobile App Integration** - React Native companion app
- [ ] **Multi-language Support** - Hindi, regional languages
- [ ] **Voice Assistant** - Speech-to-text for queries
- [ ] **Blockchain Integration** - Document authenticity verification
- [ ] **Advanced Analytics** - ML-powered insights for admins
- [ ] **API Gateway** - RESTful API for third-party integrations

***

## 🏆 Acknowledgments

| Technology | Purpose | Link |
| :-- | :-- | :-- |
| **Firebase** | Backend infrastructure | [Firebase](https://firebase.google.com/) |
| **JavaFX** | Desktop UI framework | [OpenJFX](https://openjfx.io/) |
| **RichTextFX** | Rich text editing | [RichTextFX](https://github.com/FXMisc/RichTextFX) |
| **Apache POI** | Excel export functionality | [Apache POI](https://poi.apache.org/) |
| **Gemini AI** | AI assistant capabilities | [Google AI](https://ai.google.dev/) |


***

## 📞 Support \& Contact

<div align="center">

### 🆘 Need Help?
Email :- bhagwatlipne2004@gmail.com


### 🔒 Security Notice

**⚠️ Important**: This repository does not include Firebase credentials and SDKs. You must:
- Create your own Firebase project
- Generate your own service account key
- Never commit sensitive credentials to version control

</div>

***

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

***

<div align="center">

**Made with ❤️ for legal accessibility and citizen empowerment**

*Empowering citizens through technology, one legal query at a time.*

[
[

</div>
