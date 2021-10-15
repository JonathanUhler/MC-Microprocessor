# Unzip contents of all dependencies to create a single fat jar for MC-Assembler
unzip -o src/lib/javacli.jar -d src/jv/
rm -rf src/jv/META-INF

# Compile java code
javac -cp "src/lib/*" -d src/jv/ src/src/**/*.java
