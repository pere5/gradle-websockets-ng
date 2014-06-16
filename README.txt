====Install and run instructions====

==Homebrew==

ruby -e "$(curl -fsSL https://raw.github.com/Homebrew/homebrew/go/install)"

==Building java==

brew install gradle
gradle clean build
java -jar build/libs/com.endian.org.flowerbed-0.1.0.jar

==Javascript dependencies==

brew install node
npm install -g bower
cd src/main/webapp
bower install bootstrap
bower install angular#1.3.0-beta.8
