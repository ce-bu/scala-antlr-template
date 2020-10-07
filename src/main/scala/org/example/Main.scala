package org.example;

import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import org.antlr.v4.runtime.tree.ParseTree

object ExampleVisitor extends JSONBaseVisitor[Unit] {
  override def visitJson(ctx: JSONParser.JsonContext) = {
    println(ctx.value().getText())
  }
}


object Example {
  def main(args: Array[String]): Unit = {
    val inout =
      """
        | {
        |   "name" : "example",
        |   "value" : 12.3
        | }
        |""".stripMargin
    var lexer = new JSONLexer(CharStreams.fromString(inout))
    val tokens = new CommonTokenStream(lexer)
    val parser = new JSONParser(tokens)
    val tree = parser.json()
    ExampleVisitor.visitJson(tree)

  }
}
