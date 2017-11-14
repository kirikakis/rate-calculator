# combined-loan rate-calculator

## Overview
A console rate calculation system allowing prospective borrowers to obtain a quote for loans repaid over a selected period from a given list of investors.

### Prerequisites
* Java 1.8 JDK (for installation instructions see [here](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html))

### Execution
For building the source code, running the tests and launching the application, please navigate in the root folder of the project
from your console and execute the following command:

#### Unix
```
./gradlew test run -PappArgs="src/test/resources/sample-data.csv 1500 36"
```

#### Windows
```
gradlew test run -PappArgs="src/test/resources/sample-data.csv 1500 36"
```

#### result:
```
Requested Amount: 1500.0
Rate: 7.1
Monthly repayment:46.41
Total repayment:1670.93
```