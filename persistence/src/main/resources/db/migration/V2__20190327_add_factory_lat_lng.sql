ALTER TABLE `factory`
  ADD COLUMN `lat` double NULL AFTER `status`,
  ADD COLUMN `lng` double NULL AFTER `lat`;