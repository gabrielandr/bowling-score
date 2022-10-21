package com.jobsity.bowlingscore.service;

class BowlingServiceImplTest {

//    @Test
//    void testValidatePlayerPinfallsWrongValues() {
//        BowlingServiceImpl bowlingServiceImpl = new BowlingServiceImpl(fileService);
//
//        ArrayList<String> playerPinfalls = new ArrayList<>();
//        playerPinfalls.add("D");
//        playerPinfalls.add("t");
//        playerPinfalls.add("42");
//        playerPinfalls.add("-5");
//        List<String> validatePlayerPinfallsResult = bowlingServiceImpl.validatePlayerPinfalls(playerPinfalls);
//        assertEquals(4, validatePlayerPinfallsResult.size());
//        assertEquals("Invalid Roll Score! 'D'", validatePlayerPinfallsResult.get(0));
//        assertEquals("Invalid Roll Score! 't'", validatePlayerPinfallsResult.get(1));
//        assertEquals("Invalid Roll Score! '42'", validatePlayerPinfallsResult.get(2));
//        assertEquals("Invalid Roll Score! '-5'", validatePlayerPinfallsResult.get(3));
//    }
//
//    @Test
//    void testValidatePlayerPinfallsValidValues() {
//        BowlingServiceImpl bowlingServiceImpl = new BowlingServiceImpl(fileService);
//
//        ArrayList<String> playerPinfalls = new ArrayList<>();
//        playerPinfalls.add("10");
//        playerPinfalls.add("5");
//        playerPinfalls.add("f");
//        playerPinfalls.add("0");
//        List<String> errors = bowlingServiceImpl.validatePlayerPinfalls(playerPinfalls);
//        assertTrue(errors.isEmpty());
//    }
//
//    @Test
//    void testValidateRegularScoreGame() {
//        BowlingServiceImpl bowlingServiceImpl = new BowlingServiceImpl(fileService);
//
//        List<String> rollsPlayer1 = Arrays.asList("10", "7", "3", "9", "0", "10", "0", "8", "8", "2", "0", "6", "10", "10", "10", "8", "1");
//        List<ScoreBoardDTO> scoreBoardPlayer1 = new ArrayList<>();
//        List<String> errorsPlayer1 = bowlingServiceImpl.calculatePlayerScore(rollsPlayer1, scoreBoardPlayer1);
//
//        List<String> rollsPlayer2 = Arrays.asList("3", "7", "6", "3", "10", "8", "1", "10", "10", "9", "0", "7", "3", "4", "4", "10", "9", "0");
//        List<ScoreBoardDTO> scoreBoardPlayer2 = new ArrayList<>();
//        List<String> errorsPlayer2 = bowlingServiceImpl.calculatePlayerScore(rollsPlayer2, scoreBoardPlayer2);
//
//        assertTrue(errorsPlayer1.isEmpty());
//        assertEquals("167", scoreBoardPlayer1.get(scoreBoardPlayer1.size() - 1).getScore());
//        assertTrue(errorsPlayer2.isEmpty());
//        assertEquals("151", scoreBoardPlayer2.get(scoreBoardPlayer2.size() - 1).getScore());
//    }
//
//    @Test
//    void testValidatePerfectGame() {
//        BowlingServiceImpl bowlingServiceImpl = new BowlingServiceImpl(fileService);
//
//        List<ScoreBoardDTO> scoreBoard = new ArrayList<>();
//        List<String> rollsByPlayer = Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10");
//        List<String> errors = bowlingServiceImpl.calculatePlayerScore(rollsByPlayer, scoreBoard);
//
//        assertTrue(errors.isEmpty());
//        assertEquals("300", scoreBoard.get(scoreBoard.size() - 1).getScore());
//    }
//
//    @Test
//    void testValidateAllZerosGame() {
//        BowlingServiceImpl bowlingServiceImpl = new BowlingServiceImpl(fileService);
//
//        List<ScoreBoardDTO> scoreBoard = new ArrayList<>();
//        List<String> rollsByPlayer = Arrays.asList("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
//        List<String> errors = bowlingServiceImpl.calculatePlayerScore(rollsByPlayer, scoreBoard);
//
//        assertTrue(errors.isEmpty());
//        assertEquals("0", scoreBoard.get(scoreBoard.size() - 1).getScore());
//    }
}

