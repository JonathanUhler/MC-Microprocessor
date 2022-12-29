start:
    nop                                     // 0x00:  0x0000000
    add r1, r2, r3                          // 0x01:  0x0612300
    addi r4, r5, 10                         // 0x02:  0x0E4500A
    and r5, r6, r7                          // 0x03:  0x0156700
    andi r8, r9, 0xff                       // 0x04:  0x09890FF
    brz r10, l1                             // 0x05:  0x1000A06
l1:	
    bro r11, end                            // 0x06:  0x1100B22
l2:	
    cmp r12, r13, r4                        // 0x07:  0x04CD400
    cmpi r1, r2, 0xaa                       // 0x08:  0x0C120AA
    mflo r3                                 // 0x09:  0x1230000
    mfhi r4                                 // 0x0A:  0x1240001
    not r5, r6                              // 0x0B:  0x0350600
    noti r7, 0xcc                           // 0x0C:  0x0B700CC
    or r8, r9, r10                          // 0x0D:  0x0089A00
    ori r11, r12, 0xdd                      // 0x0E:  0x08BC0DD
    sub r13, r14, r15                       // 0x0F:  0x07DEF00
    subi r1, r2, 1                          // 0x10:  0x0F12001
    li r3, 0x88                             // 0x11:  0x0830088
    nop                                     // 0x12:  0x0000000
    br l1                                   // 0x13:  0x1000006
    halt 1                                  // 0x14:  0x1F00001

	.loc 0x22
end:
	halt 2                                  // 0x22:  0x1F00002
