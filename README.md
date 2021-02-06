twilio-sms
==========
A simple web application to send and receive SMS messages using Twilio.

Tech Stack
----------
* Backend written in Java and Spring Boot
* Frontend served with Thymeleaf
* No database is required all information is handled in memory

Requirements
------------
* A Twilio account. You can [create a free Twilio account] to get started.
* Java 11

Quick Start
-----------

Download the latest `.jar` file from the [releases page], navigate to the folder where the file is saved and run:

```bash
java -jar twilio-sms-v1.0.jar
```

Open a web browser and navigate to http://localhost:8181/

Enter your Twilio's `Account SID` and `Auth Token` as described in [Twilio Auth Tokens Help Center]

[create a free Twilio account]: https://www.twilio.com/docs/usage/tutorials/how-to-use-your-free-trial-account
[Twilio Auth Tokens Help Center]: https://support.twilio.com/hc/en-us/articles/223136027-Auth-Tokens-and-How-to-Change-Them
[releases page]: https://github.com/rafasantos/twilio-sms/releases
