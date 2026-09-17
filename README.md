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

### Running the Complete Suite via Command Line
To clean target environments, compile bytecode, and execute the master TestNG suite via Maven, run:
```bash
mvn clean test -DsuiteXmlFile=testng_master.xml
```


