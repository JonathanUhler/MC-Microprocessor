# Build jar file
jar -cmf manifest.mf release/PMGenerator/PMGenerator.jar -C src/jv/ .

# Move the shell script executable
cp src/src/PMGenerator release/PMGenerator/