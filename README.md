# grossmarkt
Software engineering project for the 4th semester.
An administration tool for a superstore for vegetables. Lecturer: Bernd Lambertz

## Details
### JDK
The used JDK is openjdk-16

### Style guide
Style: https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml

Setting the style in IntelliJ: https://medium.com/swlh/configuring-google-style-guide-for-java-for-intellij-c727af4ef248

## Installing in IntelliJ
1. Clone Project
2. Add new Configuration (upper right corner) \
   2.1 Modify Options -> Add VM-option `--module-path C:\Users\koenigf\Documents\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib --add-modules=javafx.controls,javafx.fxml` (openjfx-path will vary) \
   2.2 Select grossmarkt.Main function
3. Open Project Structure -> Project Settings -> Libraries: select lib folder of jfx
