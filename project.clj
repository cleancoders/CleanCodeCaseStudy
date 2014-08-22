(defproject casestudy "cleancoderscasestudy"
  :description "Case Study"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [junit/junit "4.11"]
                 [org.fitnesse/fitnesse "20140630"]
                 [de.bechte.junit/junit-hierarchicalcontextrunner "4.11.3"]]

  :repositories [["sonatype" {:url "http://oss.sonatype.org/content/repositories/releases"}]]

  :aliases {"fitnesse" ["run" "-m" "fitnesseMain.FitNesseMain" "-p" "8080"]}
  )


