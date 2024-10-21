SRC_DIR  := src
LIB_DIR  := lib
OBJ_DIR  := obj
BIN_DIR  := bin
TEST_DIR := tests

MANIFEST := manifest.mf


.PHONY: compile \
	jar     \
	test    \
	obj_dir \
	bin_dir \
	clean

compile: obj_dir
	javac -cp '$(SRC_DIR)/$(LIB_DIR)/*' -d $(OBJ_DIR) \
		$(shell find $(SRC_DIR)/main -name '*.java')

jar: compile bin_dir
	mkdir -p $(BIN_DIR)/$(LIB_DIR)
	cp -a $(SRC_DIR)/$(LIB_DIR)/* $(BIN_DIR)/$(LIB_DIR)
	jar cmf $(MANIFEST) bin/MCAssembler.jar -C $(OBJ_DIR) .

test: jar
	javac -cp '.:$(SRC_DIR)/lib/*:$(BIN_DIR)/*' \
		-d $(OBJ_DIR)/tests                 \
		$(shell find tests -name '*.java')
	java -cp '.:$(SRC_DIR)/lib/*:$(OBJ_DIR)/tests:$(BIN_DIR)/*' \
		org.junit.runner.JUnitCore                          \
		TestTokenizer TestLocation TestLabel TestImmediate TestInstruction

obj_dir:
	mkdir -p $(OBJ_DIR)

bin_dir:
	mkdir -p $(BIN_DIR)

clean:
	@rm -rf $(BIN_DIR) $(OBJ_DIR)
