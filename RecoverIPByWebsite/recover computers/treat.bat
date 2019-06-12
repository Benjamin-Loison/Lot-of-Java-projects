@echo off
set client=51.38.49.%2
%SystemRoot%\system32\ping.exe -n 1 %client% >nul
if errorlevel 1 (
	REM echo %client% is not availabe yet.
) else (
	echo %client% >> available.txt
)
exit