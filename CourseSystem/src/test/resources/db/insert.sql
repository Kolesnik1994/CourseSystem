INSERT INTO `users` VALUES (1,'admin@gmail.conm','$2a$10$DLnKb.1SKf/ACYqnjvkG2.smBAs7/KFx/ZgIbjewdI64V7KFsujjK'),(2,'instructors@gmail.conm','$2a$10$S9A7aAL.yXCaFDnUuXppKuKo2O3FCuUPFhB3wSkUp9xMvLa25TfBO'),(3,'egor@gmail.com','$2a$10$cWUCirhLCngpWpdOMs4VlexsaZL1R3uEpo8D03Yml7xPuyKSUVUxm'),(4,'vova@gmail.com','$2a$10$2BYrF0rEuyO1zK3VjdOeFuDcx/Nn7T5YIZJZ47MxKaRL1nW6CLYU2'),(5,'student@mail.com','$2a$10$SCTAALVj3jQMQPL.tZ1nKeCxXKkgA.NbM9sUV5xBklVNzFh7HkvfS'),(6,'sdutens2@gmail.com','$2a$10$pGECeeYBCR1RZ3qMEs8PceBc5vVKwN17PRIxojf8jcS75wzwPL3PG');
INSERT INTO `instructor` VALUES (1,'Vanya','Mentor','Tutkins',3),(2,'Vova','Senior','Pupkin',4);
INSERT INTO `courses` VALUES (1,'Master Bottle','10 hours','Python',1),(2,'Rubby on Rails','8 hours','Ruby',2);
INSERT INTO `students` VALUES (1,'Amir','Ramitovich','Java',5),(2,'Rahim','Pantiks','PHP',6);
INSERT INTO `enrolled_in` VALUES (1,1),(2,2);
INSERT INTO `role` VALUES (1,'Admin'),(2,'Instructor'),(3,'Student');
INSERT INTO `user` VALUES (1,'admin@gmail.conm','$2a$10$L4rEel02IIMcDGxpMZfjq.fNoPHxQjrKuq1eqUoGrEBlC8d8tCmkG'),(2,'instructors@gmail.conm','$2a$10$xpFN2DOnwoATfpQN0eKcFOmU1thHQJuZm/jh7ELvGS.y0Oh1QSoSy'),(3,'egor@gmail.com','$2a$10$qjka5ei4sdMB1YraONuL/urtEMQVnnG2SfhDKknr66vPX7J5bgG66'),(4,'vova@gmail.com','$2a$10$dAPFrUskHrbOSsC7umdN.OwRuSQYMq/c0m4pdVrFRQSaJZWVQmu3C'),(5,'student@mail.com','$2a$10$dlFZPKgk1Ss9TUNmRXivgO8xNi0PvVCEvjxyNKZDFiGCacdT1U6xS'),(6,'sdutens2@gmail.com','$2a$10$/4sOSxTBPR9mNUFNTcxbpuq8RxdHAyPxpawKNLYwsW5lgTGwWyAme');
INSERT INTO `user_role` VALUES (1,1),(3,2),(4,2),(2,3),(5,3),(6,3);



