# AWS-SQS-SpringBoot-Integration
A hands-on lab for integrating AWS SQS (and optionally SNS) with Spring Boot using a Spring Boot starter pattern. This lab provides reusable modules, example apps, Terraform scripts, and automation for CI/CD, monitoring, and observability.

## Table of Contents 

- [Project Structure](#project-structure)

- [Prerequisites](#prerequisites)

- [Setup & Deployment](#setup--deployment)

- [Usage](#usage)

- [Modules Overview](#modules-overview)

- [Hands-On Exercises](#hands-on-exercises)

- [Cleanup](#cleanup)

- [License](#license)

## Project Structure
```
AWS-SQS_SpringBoot/
│
├── terraform/                      
│   ├── main.tf
│   ├── variables.tf
│   ├── outputs.tf
│   └── provider.tf
│
├── aws-sqs-spring-boot-starter/
│   ├── base/        # Base utilities: common config, properties binding
│   │   └── src/
│   │
│   ├── core/        # Core SQS integration: AutoConfig, beans, listener/publisher
│   │   └── src/
│   │
│   ├── starter/     # Final Spring Boot starter (combines base + core)
│   │   └── src/
│   │
│   ├── example-app-sender/       # Example: send messages to SQS (or SNS)
│   │   └── src/
│   │
│   ├── example-app-receiver/     # Example: consume messages from SQS
│   │   └── src/
│   │
│   └── pom.xml                   # Parent POM for starter + examples
│
└── scripts/
    ├── deploy.sh                 # Deploy AWS infrastructure via Terraform
    └── cleanup.sh                # Tear down infrastructure
```

## Prerequisites
- Java 17+
- Maven 3.9+
- AWS Account with IAM credentials 
- Terraform 1.5+
- (Optional) Docker / LocalStack for local testing 
- AWS Playground provided via [KodeKloud](https://learn.kodekloud.com/)

## Setup & Deployment
### Clone the repository 
```bash
git clone git@github.com:aws-spring-integration/AWS-SQS_SpringBoot.git
cd AWS-SQS_SpringBoot
```

### Deploy AWS infrastructure 
```bash
cd terraform
./deploy.sh
```

This will create SQS queues (and optionally SNS topics) required by the project.

### Build the starter and example apps 
```bash
cd aws-sqs-spring-boot-starter
mvn clean install
```

## Usage
### Local Dev Mode  
### Example Sender 
- Navigate to the sender app:

```bash 
cd example-app-sender
mvn spring-boot:run
```
The app publishes messages to the configured SQS queue (or SNS topic if configured).

### Example Receiver 

- Navigate to the receiver app:

```bash 
cd example-app-receiver
mvn spring-boot:run
```
- The app listens to SQS and processes incoming messages. 

### Local Docker Compose Mode 

// TODO here 


## Modules Overview 
- **base**: Providers **common utilities**, **base configurations**, **property binding**, and defines **SQS-related annotations** to enable listeners. 

- **core**:  Encapsulates **SQS** and **SNS service interactions**, e.g., `SqsListener, SnsPublisher`, and low-level AWS SDK operations. 
- **starter**: Combines **base** + **core**, includes **auto-configuration**, and exposes a reusable Spring Boot starter for users. 
- **example-app-sender**: Demostrates sending messages to SQS (or via SNS) using the starter. 
- **example-app-receiver**: Demostrates consuming messages from SQS using the starter. 
- **terraform**: 
- **scripts**: Automation scripts for deployment (`deploy.sh`) and cleanup (`cleanup.sh`) of AWS resources. 

## Hands-On Exercises
- Send a message from **example-app-sender** and verify it is consumed by **example-app-receiver**.
- Modify queue/topic properties in Terraform and redeploy to see changes reflected. 
- Extend the starter to add monitoring, logging, or custom transformations. 

## Cleanup
After finishing the app:
```bash
cd scripts
./cleanup.sh
```

This will remove all AWS resources created during the project execution. 

## License 
- [LICENSE](./LICENSE)