#SwagLabs Automation Framework
- Selenium Java automation project developed by Alfredo Villegas
- This project shows basic framework design practices such as use of page objects, creating separate test classes and more.


## 🛠️ Tech Stack & Architecture
* **Language:** Java 17
* **Build Tool:** Maven
* **Core Automation Library:** Selenium WebDriver (v4.48.0)
* **Test Engine:** TestNG
* **Design Pattern:** Page Object Model (POM) with a centralized `AbstractComponent` layer for reusability.
* **CI/CD Platform:** N/A [Needs manual setup using Jenkins and Github webhook trigger]
* **Reporting Engine:** ExtentReports (with dynamic ThreadLocal safety and automated failure screenshots)
* **Data Management:** Apache POI (Excel `.xlsx` ingestion) and Jackson/Gson (JSON parsing)


## 📂 Project Structure Breakdown

```text
SwagLabsTest/
├── src/main/java/
│   └── pageObjects/          # Page Object classes encapsulating UI elements and actions
│       ├── AbstractComponent.java   # Centralized Explicit Wait strategies and generic utilities
│       └── [PageObjects].java       # Individual page elements (Login, Products, Cart, Checkout)
├── src/main/resources/
│   └── GlobalData.properties # Dynamic configuration file (Environment, Browser switches)
├── src/test/java/
│   ├── data/                 # Data-driven resources (DataReader utility, .json & .xlsx files)
│   ├── test/                 # Test suites (EndToEndTest, ItemTesting, LoginValidationTest)
│   └── testComponents/       # Framework configurations (BaseTest, Listeners, ExtentReporterNG)
├── TestNG XML files/         # Modular test execution suites
├── reports/                  # Automatically generated interactive Extent HTML test dashboards
├── .github/workflows/        # CI/CD orchestration pipeline configuration
├── testng_master.xml         # Master automation suite runner
└── pom.xml                   # Maven dependencies and plugin lifecycle declarations
```


## 🚀 Key Framework Features
* **ThreadLocal Reporting Framework:** Utilizes TestNG Listeners coupled with a Java `ThreadLocal` wrapper. This ensures completely isolated, thread-safe test reporting logs and prevents layout overlapping during parallel pipeline test execution.
* **Automatic Failure Capture:** Monitors runtime lifecycles via TestNG `ITestListener`. Upon test failure, the driver is automatically intercepted to capture a base64/file system screenshot, seamlessly embedding visual failure evidence directly into the Extent HTML report.
* **Centralized Synchronization Layer:** Avoids flaky scripts by banning structural implicit wait delays for explicit conditions. Reusable wait utilities live inside `AbstractComponent`, dynamically validating DOM visibility and interactivity states before UI execution.
* **Abstracted Configuration Engine:** Eliminates hardcoded environment credentials or drivers. A central `.properties` configuration allows external runtime commands to manipulate target browsers on demand.

---

## 💻 Getting Started & Execution

### Prerequisites
* Java JDK 17
* Apache Maven (Ensure `mvn` is accessible in your system environment PATH variables)

### Running via Maven Command Line (Profiles & Browsers)
This framework supports target testing scopes through customized **Maven Profiles** and dynamically injected **Browser System Properties**. 

To run a specific test suite on a specific browser, use the following command structure:
```bash
mvn clean test -P<profileName> -Dbrowser=<browserName>
```

#### Available Test Profiles (`-P`)
* **`MasterRunner`** - Executes the full regression suite via `testng_master.xml`
* **`EndToEnd`** - Executes core user flows via `runners/EndToEndTestXML.xml`
* **`LoginValidation`** - Executes login boundary tests via `runners/LoginValidationTestXML.xml`
* **`ItemTest`** - Executes cart manipulation validations via `runners/ItemTestingXML.xml`

#### Available Browsers (`-Dbrowser=`)
* **`chrome`** - Google Chrome
* **`firefox`** - Mozilla Firefox
* **`edge`** - Microsoft Edge
* **`brave`** - Brave Browser
* **`opera`** - Opera Browser

#### Execution Examples
Run the entire regression suite on **Google Chrome**:
```bash
mvn clean test -PMasterRunner -Dbrowser=chrome
```

Run only item-adding workflows on **Mozilla Firefox**:
```bash
mvn clean test -PItemTest -Dbrowser=firefox
```

---

## 🔄 CI/CD Pipeline Architecture (Jenkins + Webhooks)

This framework is configured for local Continuous Integration testing using a **Jenkins CI Server** paired with automated triggers. 

### Pipeline Trigger Mechanics:
1. **GitHub Webhook:** A webhook is configured inside the GitHub repository settings to listen for code modifications (e.g., `git push`).
2. **Secure Tunnel via ngrok:** Since the Jenkins environment resides locally behind a private residential firewall, **ngrok** is deployed to establish a secure public URL tunnel. This enables GitHub's cloud servers to bypass network restrictions and send payloads directly to the local machine (`localhost:8080/github-webhook/`).
3. **Automated Builds:** Upon intercepting a valid GitHub push payload event, Jenkins automatically wakes up, pulls down the latest code updates, runs the Maven execution lifecycle (`mvn test`), and saves test output reporting metrics.
