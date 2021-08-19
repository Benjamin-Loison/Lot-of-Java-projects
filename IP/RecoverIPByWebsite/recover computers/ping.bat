@echo off

FOR /L %%C IN (49, 1, 49) DO (
	echo Working on %%C
	FOR /L %%D IN (0, 1, 255) DO START /b treat.bat %%C %%D & millisleep.exe 10
)
echo Every ip testing launched !
pause