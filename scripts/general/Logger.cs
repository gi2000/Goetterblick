using System;
using Godot;

namespace Goetterblick.scripts.general;

public partial class Logger : RefCounted
{
    private static readonly object Padlock = new();
    private static Logger _instance;

    public static Logger Instance
    {
        get
        {
            lock (Padlock)
            {
                return _instance ??= new Logger(Utils.LogLevel);
            }
        }
    }

    private const string TimestampFormat = "yyyy-MM-dd HH:mm:ss (fff'ms')";

    private LogLevel LogLevel { get; set; }

    private Logger(LogLevel logLevel)
    {
        LogLevel = logLevel;
    }

    public Logger()
    {
        LogLevel = LogLevel.Debug;
    }

    public void Debug(string message, bool printToGodotConsole = false)
    {
        const LogLevel currLevel = LogLevel.Debug;
        if (LogLevel < currLevel) return;
        HandleMessage(currLevel, message, printToGodotConsole);
    }

    public void Info(string message, bool printToGodotConsole = false)
    {
        const LogLevel currLevel = LogLevel.Info;
        if (LogLevel < currLevel) return;
        HandleMessage(currLevel, message, printToGodotConsole);
    }

    public void Warn(string message, bool printToGodotConsole = false)
    {
        const LogLevel currLevel = LogLevel.Warn;
        if (LogLevel < currLevel) return;
        HandleMessage(currLevel, message, printToGodotConsole);
    }

    public void Error(string message, bool printToGodotConsole = false)
    {
        const LogLevel currLevel = LogLevel.Error;
        if (LogLevel < currLevel) return;
        HandleMessage(currLevel, message, printToGodotConsole);
    }

    public void Fatal(string message, bool printToGodotConsole = false)
    {
        const LogLevel currLevel = LogLevel.Fatal;
        if (LogLevel < currLevel) return;
        HandleMessage(currLevel, message, printToGodotConsole);
    }

    public void Raw(string message, bool printToGodotConsole = false)
    {
        Console.WriteLine(message);
        if (printToGodotConsole)
        {
            GD.PrintErr(message);
        }

        EmitSignal("OnNewMessage", message);
    }

    private void HandleMessage(LogLevel currLevel, string message, bool printToGodotConsole = false)
    {
        string parsedMessage = string.Format(GetTimestamp(), currLevel, message);
        Console.WriteLine(parsedMessage);
        if (printToGodotConsole)
        {
            switch (currLevel)
            {
                case LogLevel.Fatal:
                case LogLevel.Error:
                    GD.PrintErr(parsedMessage);
                    break;
                case LogLevel.Debug:
                case LogLevel.Info:
                case LogLevel.Warn:
                default:
                    GD.Print(parsedMessage);
                    break;
            }
        }

        EmitSignal("OnNewMessage", parsedMessage);
    }

    private static string GetTimestamp()
    {
        return "[{0, 5} | " + DateTime.Now.ToString(TimestampFormat) + "] {1}";
    }

    [Signal]
    public delegate void OnNewMessageEventHandler(string message);
}