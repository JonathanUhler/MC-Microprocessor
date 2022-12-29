# NBT-Editor DOCUMENTATION
A simple editor for Minecraft's NBT data files. Can be used as a stand-alone command line tool for basic NBT parsing or as a library in conjuction with a larger program. \
Intended for use in the MC-Assembler suite of projects. \
Project created 10/23/21 -- begin Documentation


# Unpacked NBT
*NBT, Named Binary Tag, is a file format created for the videogame Minecraft to handle simple data processing.* \
*NBT is similar to other markup languages such as JSON and YAML. It uses a system of typed tags to store data.* \
*NBT-Editor takes some liberty in defining specific syntax to use when "programming" using unpacked NBT*

## NBT Tags
| Tag Name      | Tag ID | Payload Size (bytes) | Description                                                                     |
|---------------|--------|----------------------|---------------------------------------------------------------------------------|
| TAG_End       | 0      | 0                    | 0x00 byte that represents the end of a TAG_Compound. Not used by the programmer |
| TAG_Byte      | 1      | 1                    | A single signed byte                                                            |
| TAG_Short     | 2      | 2                    | A single signed, big endian, 2 byte integer                                     |
| TAG_Int       | 3      | 4                    | A single signed, big endian, 4 byte integer                                     |
| TAG_Long      | 4      | 8                    | A single signed, big endian, 8 byte integer                                     |
| TAG_Float     | 5      | 4                    | A single, big endian floating point number                                      |
| TAG_Double    | 6      | 8                    | A single, big endian, double-precision number                                   |
| TAG_ByteArray | 7      | varies               | An array of TAG_Byte-type values                                                |
| TAG_String    | 8      | varies               | A series of 1-byte UTF-8 characters                                             |
| TAG_List      | 9      | varies               | A list of nameless tags of the same type                                        |
| TAG_Compound  | 10     | varies               | An unordered map of named tags                                                  |
| TAG_IntArray  | 11     | varies               | An array of TAG_Int-type values                                                 |
| TAG_LongArray | 12     | varies               | An array of TAG_Long-type values                                                |

## Unpacked Tag Syntax
### TAG_End
TAG_End is not used by the programmer and is added automatically at the end of a TAG_Compound

### TAG_Byte
TAG_Byte declaration follows:
```
TAG_Byte %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_Short
TAG_Short declaration follows:
```
TAG_Short %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_Int
TAG_Int declaration follows:
```
TAG_Int %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_Long
TAG_Long declaration follows:
```
TAG_Long %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_Float
TAG_Float declaration follows:
```
TAG_Float %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_Double
TAG_Double declaration follows:
```
TAG_Double %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_String
TAG_String declaration follows:
```
TAG_String %name = %value
```
where
* name -- the programmer-defined name for the tag
* value -- the programmer-defined value for the tag

### TAG_ByteArray
TAG_ByteArray declaration follows:
```
TAG_ByteArray %name %length = [
    %value, %value,
    %value
]
```
where
* name -- the programmer-defined name for the tag
* length -- the number of elements in the array
* value -- the elements of the array, separated by commas (",")

### TAG_List
TAG_List declaration follows:
```
TAG_List %name %length = (
    %value
    %value
)
```
where
* name -- the programmer-defined name for the tag
* length -- the number of elements in the list
* value -- the elements of the list, themselves unnamed tags of the same type

### TAG_Compound
TAG_Compound declaration follows:
```
TAG_Compound %name %length = {
    %value
    %value
}
```
where
* name -- the programmer-defined name for the tag
* length -- the number of elements in the compound
* value -- the elements of the compound, themselves named tags of any type

### TAG_IntArray
TAG_IntArray declaration follows:
```
TAG_IntArray %name %length = [
    %value, %value,
    %value
]
```
where
* name -- the programmer-defined name for the tag
* length -- the number of elements in the array
* value -- the elements of the array, separated by commas (",")

### TAG_LongArray
TAG_LongArray declaration follows:
```
TAG_LongArray %name %length = [
    %value, %value,
    %value
]
```
where
* name -- the programmer-defined name for the tag
* length -- the number of elements in the array
* value -- the elements of the array, separated by commas (",")

## Other Syntax
### Comments
NBT-Editor supports comments in unpacked NBT. \
Comments are defined by the starting string "//". Anything after the comment identifier is removed and ignored by NBT-Editor.

### Token Spacing
Each element or token MUST be on a separate line. This rule extends to brackets, which must always be on separate lines.


# Packed NBT
*NBT-Editor packs uncompressed NBT instructions into hex bytes that can be parsed by Minecraft and its modifications.*

## Unpacked Tag Syntax
### TAG_End
TAG_End packs in the format:

| Name      | Bytes | Description                                                |
|-----------|-------|------------------------------------------------------------|
| zero byte | 0     | Only byte of TAG_End, represents the end of a TAG_Compound |

Example:
```
00    // TAG_End
```

### TAG_Byte
TAG_Byte packs in the format:

| Name           | Bytes   | Description                                       |
|----------------|---------|---------------------------------------------------|
| type           | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | n+2-n+1 | The number of characters in the name              |
| name           | n-1     | The characters making up the tag's name           |
| value          | 0       | The value of the tag                              |

Example:
```
01 0001 61 01    // TAG_Byte a = 1
```

### TAG_Short
TAG_Short packs in the format:

| Name           | Bytes   | Description                                       |
|----------------|---------|---------------------------------------------------|
| type           | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | n+2-n+1 | The number of characters in the name              |
| name           | n-2     | The characters making up the tag's name           |
| value          | 1-0     | The value of the tag                              |

Example:
```
02 0001 61 0001    // TAG_Short a = 1
```

### TAG_Int
TAG_Int packs in the format:

| Name           | Bytes   | Description                                       |
|----------------|---------|---------------------------------------------------|
| type           | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | n+2-n+1 | The number of characters in the name              |
| name           | n-4     | The characters making up the tag's name           |
| value          | 3-0     | The value of the tag                              |

Example:
```
03 0001 61 00000001    // TAG_Int a = 1
```

### TAG_Long
TAG_Long packs in the format:

| Name           | Bytes   | Description                                       |
|----------------|---------|---------------------------------------------------|
| type           | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | n+2-n+1 | The number of characters in the name              |
| name           | n-7     | The characters making up the tag's name           |
| value          | 7-0     | The value of the tag                              |

Example:
```
04 0001 61 0000000000000001    // TAG_Long a = 1
```

### TAG_Float
TAG_Float packs in the format:

| Name           | Bytes   | Description                                       |
|----------------|---------|---------------------------------------------------|
| type           | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | n+2-n+1 | The number of characters in the name              |
| name           | n-3     | The characters making up the tag's name           |
| value          | 3-0     | The value of the tag                              |

Example:
```
05 0001 61 3f800000    // TAG_Float a = 1
```

### TAG_Double
TAG_Double packs in the format:

| Name           | Bytes   | Description                                       |
|----------------|---------|---------------------------------------------------|
| type           | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | n+2-n+1 | The number of characters in the name              |
| name           | n-7     | The characters making up the tag's name           |
| value          | 7-0     | The value of the tag                              |

Example:
```
06 0001 61 3ff0000000000000    // TAG_Double a = 1
```

### TAG_String
TAG_String packs in the format:

| Name            | Bytes   | Description                                       |
|-----------------|---------|---------------------------------------------------|
| type            | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name  | n+2-n+1 | The number of characters in the name              |
| name            | n-v+3   | The characters making up the tag's name           |
| length of value | v+2-v+1 | The length of the string value                    |
| value           | v-0     | The value of the tag                              |

Example:
```
08 0001 61 0001 62    // TAG_String a = b
```

### TAG_ByteArray
TAG_ByteArray packs in the format:

| Name            | Bytes   | Description                                       |
|-----------------|---------|---------------------------------------------------|
| type            | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name  | n+2-n+1 | The number of characters in the name              |
| name            | n-v+5   | The characters making up the tag's name           |
| length of value | v+4-v+1 | The length of the array                           |
| value           | v-0     | The value of the tag                              |

Example:
```
07 0001 61 00000001 03    // TAG_ByteArray a 1 = [
                          //     3
                          // ]
```

### TAG_List
TAG_List packs in the format:

| Name            | Bytes   | Description                                      |
|-----------------|---------|--------------------------------------------------|
| type            | n+3     | The type (see UnpackedNBT > NBT Tags) of the tag |
| length of name  | n+2-n+1 | The number of characters in the name             |
| name            | n-v+6   | The characters making up the tag's name          |
| value type      | v+5     | The type of data stored in the list              |
| length of value | v+4-v+1 | The length of the list                           |
| value           | v-0     | The value(s) of the list                         |

Example:
```
09 0001 61 01 00000001 02    // TAG_List a 1 = (
                             //    TAG_Byte 0 = 2
                             // )
```

### TAG_Compound
TAG_Compound packs in the format:

| Name           | Bytes    | Description                                       |
|----------------|----------|---------------------------------------------------|
| type           | varies   | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name | varies   | The number of characters in the name              |
| name           | varies   | The characters making up the tag's name           |
| value          | varies-0 | The values of the tag                             |
| end byte       | 0        | A TAG_End                                         |

Example:
```
0a 0001 61 0100016202 00    // TAG_Compound a 1 = {
                            //     TAG_Byte b = 2
                            // }
```

### TAG_IntArray
TAG_IntArray packs in the format:

| Name            | Bytes   | Description                                       |
|-----------------|---------|---------------------------------------------------|
| type            | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name  | n+2-n+1 | The number of characters in the name              |
| name            | n-v+5   | The characters making up the tag's name           |
| length of value | v+4-v+1 | The length of the array value                     |
| value           | v-0     | The value of the tag                              |

Example:
```
07 0001 61 00000001 00000003    // TAG_IntArray a 1 = [
                                //     3
                                // ]
```

### TAG_LongArray
TAG_LongArray packs in the format:

| Name            | Bytes   | Description                                       |
|-----------------|---------|---------------------------------------------------|
| type            | n+3     | The type (see Unpacked NBT > NBT Tags) of the tag |
| length of name  | n+2-n+1 | The number of characters in the name              |
| name            | n-v+5   | The characters making up the tag's name           |
| length of value | v+4-v+1 | The length of the array value                     |
| value           | v-0     | The value of the tag                              |

Example:
```
07 0001 61 00000001 0000000000000003    // TAG_LongArray a 1 = [
                                        //     3
                                        // ]
```
