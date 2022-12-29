# Unzip contents of all dependencies to create a single fat jar
unzip -oq src/lib/javacli.jar -d src/jv/
rm -rf src/jv/META-INF
unzip -oq src/lib/NBTEditor.jar -d src/jv/
rm -rf src/jv/META-INF


# Compile java code
javac -cp "src/lib/*" -d src/jv/ $(find ./src/ -name '*.java')
