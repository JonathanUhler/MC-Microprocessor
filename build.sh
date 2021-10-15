# Build MC-Assembler jar file
jar -cvmf manifest.mf release/MCasm/MCasm.jar -C src/jv/ .

# Move the shell script executable
cp src/src/MCasm release/MCasm/