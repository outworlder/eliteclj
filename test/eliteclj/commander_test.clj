(ns eliteclj.commander-test
  (:require [clojure.test :refer :all]
            [eliteclj.commander :refer :all]))

(deftest test-commander-creation
  (let [commander (create-commander "Jameson")]
    (testing "Commander should have 100 credits at first"
      (is (= 100 (:credits commander))))
    (testing "Commander should start with no criminal records"
      (is (= :clean (:legal-status commander))))))
