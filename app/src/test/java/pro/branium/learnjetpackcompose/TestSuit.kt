package pro.branium.learnjetpackcompose

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    TestClass1::class,
    TestClass2::class,
    TestClass3::class,
    TestDateParser::class,
)
class LoginFeatureTestSuite