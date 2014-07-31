(ns eliteclj.commander-test
  (:require [clojure.test :refer :all]
            [eliteclj.commander :refer :all]))

(deftest test-commander-creation
  (let [commander (create-commander "Jameson")]
    (testing "Commander should have 100 credits at first"
      (is (= 100 (:credits commander))))
    (testing "Commander should start with no criminal records"
      (is (= :clean (:legal-status commander))))
    (testing "Commander should start with zero kills"
      (is (= 0 (:kills commander))))
    (testing "Commander should start at Lave"
      (is (= "Lave" (:current-system commander))))
    (testing "The initial Elite Rating should be 'harmless'"
      (is (= "Harmless" (:elite-rating commander))))))

(deftest test-elite-ratings
  (let [commander-noob (create-commander "Noob")]
    (testing "A commander can kill!"
      (is (= 1 (:kills (confirmed-kill commander-noob)))))
    (testing "8 kills will grant the commander the 'Mostly Harmless' status"
      (is (= "Mostly Harmless"
             (:elite-rating (last (take 9 (iterate confirmed-kill commander-noob)))))))
    (testing "16 kills will grant the commander the 'Poor' status"
      (is (= "Poor"
             (:elite-rating (last (take 17 (iterate confirmed-kill commander-noob)))))))
    (testing "32 kills will grant the commander the 'Average' status"
      (is (= "Average"
             (:elite-rating (last (take 33 (iterate confirmed-kill commander-noob)))))))
    (testing "64 kills will grant the commander the 'Above Average' status"
      (is (= "Above Average"
             (:elite-rating (last (take 65 (iterate confirmed-kill commander-noob)))))))
    (testing "128 kills will grant the commander the 'Competent' status"
      (is (= "Competent"
             (:elite-rating (last (take 129 (iterate confirmed-kill commander-noob)))))))
    (testing "512 kills will grant the commander the 'Dangerous' status"
      (is (= "Dangerous"
             (:elite-rating (last (take 513 (iterate confirmed-kill commander-noob)))))))
    (testing "2560 kills will grant the commander the 'Deadly' status"
      (is (= "Deadly"
             (:elite-rating (last (take 2561 (iterate confirmed-kill commander-noob)))))))
    (testing "6400 kills will grant the commander the 'Elite' status"
      (is (= "Elite"
             (:elite-rating (last (take 6401 (iterate confirmed-kill commander-noob)))))))))

(deftest right-on-commander
  (let [num-calls (atom 0)
        right-on-commander
        (merge
         (create-commander "Awesome")
         { :right-on-commander (fn []
                                 (do (swap! num-calls inc)) @num-calls)
           :right-on-commander-calls (fn []
                                       @num-calls)})]
    (testing "Should call the Right on, Commander! function every 256 kills"
      (is (= 25
             ((:right-on-commander-calls (last (take 6401 (iterate confirmed-kill right-on-commander))))))))))
