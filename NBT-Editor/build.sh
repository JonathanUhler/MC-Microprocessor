# Build jar file
echo "Creating jar file..."
jar -cmf manifest.mf release/NBTEditor/NBTEditor.jar -C src/jv/ .
echo "Done"

# Move the shell script executable
echo "Building package structure..."
cp src/src/NBTEditor release/NBTEditor/
echo "Done"