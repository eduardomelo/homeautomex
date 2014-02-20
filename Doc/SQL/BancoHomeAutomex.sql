USE [master]
GO
/****** Object:  Database [HomeAutomexdb]    Script Date: 19/02/2014 21:11:15 ******/
CREATE DATABASE [HomeAutomexdb]
-- CONTAINMENT = NONE
-- ON  PRIMARY 
--( NAME = N'HomeAutomexdb', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\HomeAutomexdb.mdf' , SIZE = 4160KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
-- LOG ON 
--( NAME = N'HomeAutomexdb_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\HomeAutomexdb_log.ldf' , SIZE = 1040KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
--GO
--ALTER DATABASE [HomeAutomexdb] SET COMPATIBILITY_LEVEL = 110
--GO
--IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
--begin
--EXEC [HomeAutomexdb].[dbo].[sp_fulltext_database] @action = 'enable'
--end
--GO
--ALTER DATABASE [HomeAutomexdb] SET ANSI_NULL_DEFAULT OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET ANSI_NULLS OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET ANSI_PADDING OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET ANSI_WARNINGS OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET ARITHABORT OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET AUTO_CLOSE ON 
--GO
--ALTER DATABASE [HomeAutomexdb] SET AUTO_CREATE_STATISTICS ON 
--GO
--ALTER DATABASE [HomeAutomexdb] SET AUTO_SHRINK OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET AUTO_UPDATE_STATISTICS ON 
--GO
--ALTER DATABASE [HomeAutomexdb] SET CURSOR_CLOSE_ON_COMMIT OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET CURSOR_DEFAULT  GLOBAL 
--GO
--ALTER DATABASE [HomeAutomexdb] SET CONCAT_NULL_YIELDS_NULL OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET NUMERIC_ROUNDABORT OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET QUOTED_IDENTIFIER OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET RECURSIVE_TRIGGERS OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET  ENABLE_BROKER 
--GO
--ALTER DATABASE [HomeAutomexdb] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET DATE_CORRELATION_OPTIMIZATION OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET TRUSTWORTHY OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET ALLOW_SNAPSHOT_ISOLATION OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET PARAMETERIZATION SIMPLE 
--GO
--ALTER DATABASE [HomeAutomexdb] SET READ_COMMITTED_SNAPSHOT OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET HONOR_BROKER_PRIORITY OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET RECOVERY SIMPLE 
--GO
--ALTER DATABASE [HomeAutomexdb] SET  MULTI_USER 
--GO
--ALTER DATABASE [HomeAutomexdb] SET PAGE_VERIFY CHECKSUM  
--GO
--ALTER DATABASE [HomeAutomexdb] SET DB_CHAINING OFF 
--GO
--ALTER DATABASE [HomeAutomexdb] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
--GO
--ALTER DATABASE [HomeAutomexdb] SET TARGET_RECOVERY_TIME = 0 SECONDS 
--GO
USE [HomeAutomexdb]
GO
/****** Object:  Table [dbo].[AGENDAMENTO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AGENDAMENTO](
	[CD_AGENDAMENTO] [int] IDENTITY(1,1) NOT NULL,
	[CD_USUARIO] [int] NOT NULL,
	[CD_CENARIO] [int] NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[IS_ATIVO] [bit] NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_AGENDAMENTO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[AMBIENTE]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[AMBIENTE](
	[CD_AMBIENTE] [int] IDENTITY(1,1) NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_AMBIENTE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CENARIO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CENARIO](
	[CD_CENARIO] [int] IDENTITY(1,1) NOT NULL,
	[CD_DISPOSITIVO] [int] NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_CENARIO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DISPOSIVO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DISPOSIVO](
	[CD_DISPOSITIVO] [int] IDENTITY(1,1) NOT NULL,
	[DS_DESCRICAO] [varchar](200) NOT NULL,
	[CD_PORTA] [int] NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_DISPOSITIVO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MODULO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MODULO](
	[CD_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[NM_IP] [varchar](50) NOT NULL,
	[NM_PORTA] [varchar](10) NOT NULL,
	[DS_NOME] [varchar](100) NOT NULL,
	[NM_MAC] [varchar](20) NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
 CONSTRAINT [PK_MODULO] PRIMARY KEY CLUSTERED 
(
	[CD_MODULO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NIVEL_ACESSO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NIVEL_ACESSO](
	[CD_NIVEL_ACESSO] [int] IDENTITY(1,1) NOT NULL,
	[DS_NOME] [varchar](200) NOT NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_NIVEL_ACESSO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PERFIL]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PERFIL](
	[CD_PERFIL] [int] NOT NULL,
	[DS_NOME] [varchar](100) NOT NULL,
	[IS_ADMIN] [bit] NOT NULL,
	[DT_CADASTRO] [datetime] NOT NULL,
	[DT_ALTERACAO] [datetime] NOT NULL,
	[DT_EXCLUSAO] [datetime] NOT NULL,
	[IS_DESATIVADO] [bit] NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
 CONSTRAINT [PK_PERFIL] PRIMARY KEY CLUSTERED 
(
	[CD_PERFIL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PORTA_MODULO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PORTA_MODULO](
	[CD_PORTA_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[NM_IDENTIFICADOR] [nchar](10) NULL,
	[NM_PORTA] [varchar](5) NULL,
	[CD_TIPO] [int] NOT NULL,
	[CD_MODULO] [int] NOT NULL,
 CONSTRAINT [PK_PORTA_MODULO] PRIMARY KEY CLUSTERED 
(
	[CD_PORTA_MODULO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RESIDENCIA]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RESIDENCIA](
	[CD_RESIDENCIA] [int] IDENTITY(1,1) NOT NULL,
	[DS_LOGRADOURO] [varchar](200) NOT NULL,
	[DS_CIDADE] [varchar](50) NOT NULL,
	[DS_BAIRRO] [varchar](50) NOT NULL,
	[NM_CEP] [varchar](50) NULL,
	[NM_NUMERO] [varchar](50) NULL,
	[DS_COMPLEMENTO] [varchar](200) NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_RESIDENCIA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TIPO_PORTA_MODULO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TIPO_PORTA_MODULO](
	[CD_TIPO_POTA_MODULO] [int] IDENTITY(1,1) NOT NULL,
	[DS_TIPO] [varchar](100) NOT NULL,
	[DS_IDENTIFICADOR] [char](1) NOT NULL,
 CONSTRAINT [PK_TIPO_PORTA_MODULO] PRIMARY KEY CLUSTERED 
(
	[CD_TIPO_POTA_MODULO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USUARIO]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[USUARIO](
	[CD_USUARIO] [int] IDENTITY(1,1) NOT NULL,
	[DS_NOME] [varchar](200) NOT NULL,
	[DS_USUARIO] [varchar](200) NOT NULL,
	[NM_TELEFONE] [varchar](50) NULL,
	[NM_CELULAR] [varchar](50) NULL,
	[DS_EMAIL] [varchar](50) NULL,
	[DS_SENHA] [varbinary](50) NULL,
	[DT_CADASTRO] [datetime] NULL,
	[DT_ALTERACAO] [datetime] NULL,
	[DT_EXCLUSAO] [datetime] NULL,
	[IS_DESATIVADO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[CD_USUARIO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USUARIO_RESIDENCIA]    Script Date: 19/02/2014 21:11:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USUARIO_RESIDENCIA](
	[CD_USUARIO_RESIDENCIA] [int] IDENTITY(1,1) NOT NULL,
	[CD_USUARIO] [int] NOT NULL,
	[CD_RESIDENCIA] [int] NOT NULL,
 CONSTRAINT [PK_USUARIO_RESIDENCIA] PRIMARY KEY CLUSTERED 
(
	[CD_USUARIO_RESIDENCIA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[PERFIL] ADD  CONSTRAINT [DF_PERFIL_IS_DESATIVADO]  DEFAULT ((0)) FOR [IS_DESATIVADO]
GO
ALTER TABLE [dbo].[USUARIO] ADD  CONSTRAINT [DF_USUARIO_IS_DESATIVADO]  DEFAULT ((0)) FOR [IS_DESATIVADO]
GO
ALTER TABLE [dbo].[DISPOSIVO]  WITH CHECK ADD  CONSTRAINT [FK_DISPOSIVO_PORTA_MODULO] FOREIGN KEY([CD_PORTA])
REFERENCES [dbo].[PORTA_MODULO] ([CD_PORTA_MODULO])
GO
ALTER TABLE [dbo].[DISPOSIVO] CHECK CONSTRAINT [FK_DISPOSIVO_PORTA_MODULO]
GO
ALTER TABLE [dbo].[MODULO]  WITH CHECK ADD  CONSTRAINT [FK_MODULO_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[MODULO] CHECK CONSTRAINT [FK_MODULO_RESIDENCIA]
GO
ALTER TABLE [dbo].[PERFIL]  WITH CHECK ADD  CONSTRAINT [FK_PERFIL_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[PERFIL] CHECK CONSTRAINT [FK_PERFIL_RESIDENCIA]
GO
ALTER TABLE [dbo].[PORTA_MODULO]  WITH CHECK ADD  CONSTRAINT [FK_PORTA_MODULO_MODULO] FOREIGN KEY([CD_MODULO])
REFERENCES [dbo].[MODULO] ([CD_MODULO])
GO
ALTER TABLE [dbo].[PORTA_MODULO] CHECK CONSTRAINT [FK_PORTA_MODULO_MODULO]
GO
ALTER TABLE [dbo].[PORTA_MODULO]  WITH CHECK ADD  CONSTRAINT [FK_PORTA_MODULO_TIPO_PORTA_MODULO] FOREIGN KEY([CD_TIPO])
REFERENCES [dbo].[TIPO_PORTA_MODULO] ([CD_TIPO_POTA_MODULO])
GO
ALTER TABLE [dbo].[PORTA_MODULO] CHECK CONSTRAINT [FK_PORTA_MODULO_TIPO_PORTA_MODULO]
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_RESIDENCIA] FOREIGN KEY([CD_RESIDENCIA])
REFERENCES [dbo].[RESIDENCIA] ([CD_RESIDENCIA])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_RESIDENCIA]
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA]  WITH CHECK ADD  CONSTRAINT [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA] FOREIGN KEY([CD_USUARIO])
REFERENCES [dbo].[USUARIO] ([CD_USUARIO])
GO
ALTER TABLE [dbo].[USUARIO_RESIDENCIA] CHECK CONSTRAINT [FK_USUARIO_RESIDENCIA_USUARIO_RESIDENCIA]
GO
USE [master]
GO
ALTER DATABASE [HomeAutomexdb] SET  READ_WRITE 
GO
