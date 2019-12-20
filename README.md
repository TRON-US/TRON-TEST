# This project used to  automated testing for TRON-US product

###  1 . Test for Android TRON-LINK App


Tests are written in Java, using Appium and TestNG

You can use these tests as templates for your own tests. Simply edit the capabilities and the test method to fit your needs.

# Running the tests.

## Running the tests from your machine
You can clone the repository to your machine and open it in your IDE of choice. The gradle file is already configured to run all tests in parallel so you can see all tests in real time without having to yet for them to run sequentially.
You can run single tests should you choose to.
The tests rely on an access key that allows you to send test requests to seetest.io cloud. In the code, the access key tries to look for an access key as
environment variable. If you haven't configured an access key as environment variable, you will have to specify it directly in the code.

To run the test from your IDE after having downloaded the code, simply run `gradlew android.com.tronlink`
