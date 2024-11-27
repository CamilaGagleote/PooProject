rmdir build /S /Q

mkdir build


javac --module-path "C:\Users\gagle\Downloads\openjfx-20.0.2_windows-x64_bin-sdk\javafx-sdk-20.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp .;./src;./lib/mariadb-java-client-3.5.0.jar -d ./build ./src/edu/curso/*.java

java --module-path "C:\Users\gagle\Downloads\openjfx-20.0.2_windows-x64_bin-sdk\javafx-sdk-20.0.2\lib" --add-modules javafx.controls,javafx.fxml -cp .;./build;./lib/mariadb-java-client-3.5.0.jar %1