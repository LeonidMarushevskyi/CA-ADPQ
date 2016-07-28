Integration Tests
============

Protractor will automatically start and stop a selenium instance and use a npm provided chromedriver. 

You'll need to install the webdriver-manager (selenium wrapper) first before running the tests:

    # See note below if you have issues with XCode on OS X
    sudo npm install -g protractor@2 cucumber@0.7.0 webdriver-manager
    sudo webdriver-manager update
    webdriver-manager start
    
Next, you can run the tests with:

    npm install
    protractor config/protractor.js 

To override the base url

    protractor --baseUrl https://chhs-prototype.portlandwebworks.com config/protractor.js

If have an error installing some node modules because of xcode complaining:

    xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance


You can get around this by running: 

    sudo xcode-select -switch /Library/Developer/CommandLineTools
