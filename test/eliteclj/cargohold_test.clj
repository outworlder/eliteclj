(ns eliteclj.commander-test
  (:require [clojure.test :refer :all]
            [eliteclj.cargohold :refer :all]))

(deftest cargohold-creation
  (testing "Can create a cargohold with a given capacity"
    (is (= 10 (:capacity (create-cargohold 10)))))
  (testing "Initial item list should be empty"
    (is (empty (:items (create-cargohold 10))))))

(deftest item-addition
  (testing "Can add an item"
    (is (= ["Laser" "Docking Computer"]
           (add-item
            (add-item
             (create-cargohold 10)
             "Docking Computer")
            "Laser")))))
