# Unzip contents of all dependencies to create a single fat jar
echo "Unzipping javacli jarfile..."
unzip -oq src/lib/javacli.jar -d src/jv/
rm -rf src/jv/META-INF
echo "Done"


# Compile java code
echo "Compiling java code..."
javac -cp "src/lib/*" -d src/jv/ $(find ./src/ -name '*.java')
echo "Done"
